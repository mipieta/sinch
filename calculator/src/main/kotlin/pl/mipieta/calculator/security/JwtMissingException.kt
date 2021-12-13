package pl.mipieta.calculator.security

import org.springframework.security.core.AuthenticationException

internal class JwtMissingException(msg: String) : AuthenticationException(msg)