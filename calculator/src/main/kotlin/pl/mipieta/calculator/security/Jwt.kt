package pl.mipieta.calculator.security

import java.util.*

internal class Jwt(val username: String, val authorities: List<String>, val expiration: Date) {
}