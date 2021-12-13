package pl.mipieta.calculator.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import java.time.Clock
import java.util.*

const val DAY = 24 * 60 * 60 * 1000

private const val AUTHORITIES = "authorities"

@Suppress("UNCHECKED_CAST")
internal class JwtUtil(private val signingSecret: String, @Autowired private val clock: Clock) {

    fun buildJWT(username: String): String {
        val now = clock.millis()
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(now + DAY))
            .claim(AUTHORITIES, listOf("ROLE_USER"))
            .signWith(Keys.hmacShaKeyFor(signingSecret.toByteArray()))
            .compact()
    }

    fun parseJWT(token: String): Jwt {
        val body = Jwts.parserBuilder().setSigningKey(signingSecret.toByteArray()).build().parseClaimsJws(token).body
        return Jwt(body.subject, body[AUTHORITIES] as List<String>, body.expiration)
    }
}
