package pl.mipieta.calculator.security

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit

internal class JwtUtilTest {
    @Test
    fun `Can build and then parse the same token`() {
        val clock: Clock = mock(Clock::class.java)
        val now = Instant.now().truncatedTo(ChronoUnit.SECONDS).toEpochMilli()

        whenever(clock.millis()).thenReturn(now)
        val secret = (0..31).joinToString("") { _ -> "x" }
        val jwtUtil = JwtUtil(secret, clock)
        val jwt = jwtUtil.buildJWT("michal")
        val parsed = jwtUtil.parseJWT(jwt)

        assertAll(
            Executable { assertEquals("michal", parsed.username) },
            Executable { assert(parsed.authorities.contains("ROLE_USER")) },
            Executable { assertEquals(now + 24 * 60 * 60 * 1000, parsed.expiration.time) },
        )
    }
}
