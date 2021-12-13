package pl.mipieta.calculator

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import pl.mipieta.calculator.security.JwtAuthenticationFilter
import pl.mipieta.calculator.security.JwtAuthenticationProvider
import pl.mipieta.calculator.security.JwtUtil
import java.time.Clock

internal class SecurityConfig {
    @Configuration
    @Order(1)
    internal class ApiSecurityConfig(@Value("\${jwt.secret}") private val signingSecret: String) :
        WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http
                .antMatcher("/api/**")
                .csrf().disable() //jwt is invulnerable to csrf
                .authorizeRequests().anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        //not a @Bean, because it would get picked up as a regular servlet filter, outside the security filter chain
        fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
            val jwtAuthenticationFilter = JwtAuthenticationFilter()
            jwtAuthenticationFilter.setAuthenticationManager(ProviderManager(listOf(authenticationProvider())))
            jwtAuthenticationFilter.setAuthenticationSuccessHandler { _, _, _ -> run {/*do not redirect*/ } }
            return jwtAuthenticationFilter
        }

        @Bean
        fun clock(): Clock {
            return Clock.systemDefaultZone()
        }

        @Bean
        fun jwtUtil() = JwtUtil(signingSecret, clock())

        @Bean
        fun authenticationProvider() = JwtAuthenticationProvider(jwtUtil())
    }

    @Configuration
    @Order(2)
    internal class NonApiSecurityConfig(
        @Value("\${admin.username}") val adminUsername: String,
        @Value("{noop}\${admin.password}") val adminPassword: String,
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http
                .antMatcher("/auth/token")
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN_ROLE").and()
                .httpBasic().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth.inMemoryAuthentication().withUser(adminUsername).password(adminPassword).roles("ADMIN_ROLE")
        }
    }
}
