package pl.mipieta.calculator.operations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class MultiplicationTest {
    @Test
    fun `Multiplication gives mathematically correct result`() {
        val result = Multiplication().evaluate(BigDecimal("1.0102"), BigDecimal("2"))
        assertEquals(BigDecimal("2.0204"), result)
    }
}
