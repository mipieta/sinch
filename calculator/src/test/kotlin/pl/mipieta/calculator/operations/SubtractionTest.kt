package pl.mipieta.calculator.operations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class SubtractionTest {
    @Test
    fun `Subtraction gives mathematically correct result`() {
        val result = Subtraction().evaluate(BigDecimal("1.01"), BigDecimal("0.01"))
        assertEquals(BigDecimal("1.00"), result)
    }
}
