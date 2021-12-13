package pl.mipieta.calculator.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

internal class JwtAuthenticationToken(val token: String) : UsernamePasswordAuthenticationToken(null, null) {

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return null
    }
}
