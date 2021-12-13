package pl.mipieta.calculator.security

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

internal class JwtAuthenticationProvider(private val jwtUtil: JwtUtil) : AbstractUserDetailsAuthenticationProvider() {

    override fun supports(authentication: Class<*>?): Boolean {
        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    override fun additionalAuthenticationChecks(
        userDetails: UserDetails, authentication: UsernamePasswordAuthenticationToken,
    ) {
    }

    override fun retrieveUser(username: String, authentication: UsernamePasswordAuthenticationToken): UserDetails {
        val jwtAuthenticationToken: JwtAuthenticationToken = authentication as JwtAuthenticationToken
        val token = jwtAuthenticationToken.token

        try {
            val parsed: Jwt = jwtUtil.parseJWT(token)
            val authorityList = AuthorityUtils.createAuthorityList(*parsed.authorities.toTypedArray())
            val expired = parsed.expiration.before(Date())
            return User(parsed.username, token, true, true, !expired, true, authorityList)
        } catch (e: Exception) {
            throw BadCredentialsException("Invalid token")
        }
    }
}
