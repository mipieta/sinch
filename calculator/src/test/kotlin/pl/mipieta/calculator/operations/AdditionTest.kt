package pl.mipieta.calculator.operations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class AdditionTest {
    @Test
    fun `Addition gives mathematically correct result`() {
        val result = Addition().evaluate(BigDecimal("1.1111"), BigDecimal("2.0304"))
        assertEquals(BigDecimal("3.1415"), result)
    }
}
