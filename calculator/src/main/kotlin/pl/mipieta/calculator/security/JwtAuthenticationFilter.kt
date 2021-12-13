package pl.mipieta.calculator.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val BEARER_ = "Bearer "

internal class JwtAuthenticationFilter : AbstractAuthenticationProcessingFilter("/**") {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val header = request.getHeader("Authentication")

        if (header == null || !header.startsWith(BEARER_)) {
            throw JwtMissingException("No JWT found in request headers")
        }

        val authToken = header.substring(BEARER_.length)

        val authRequest = JwtAuthenticationToken(authToken)

        return authenticationManager.authenticate(authRequest)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain,
        authResult: Authentication?,
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain.doFilter(request, response)
    }
}
