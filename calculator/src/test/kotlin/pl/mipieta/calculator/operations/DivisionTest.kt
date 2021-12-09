package pl.mipieta.calculator.operations

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.function.Executable
import java.math.BigDecimal

internal class DivisionTest {
    @Test
    fun `Division gives mathematically correct result`() {
        val result = Division().evaluate(BigDecimal("2.2222"), BigDecimal("2"))
        assertEquals(BigDecimal("1.1111"), result)
    }

    @Test
    fun `Division by zero throws exception`() {
        val division = Division()
        assertAll(
            Executable { assertThrows<ArithmeticException> { division.evaluate(BigDecimal("5"), BigDecimal("0")) } },
            Executable { assertThrows<ArithmeticException> { division.evaluate(BigDecimal("0"), BigDecimal("0")) } })
    }
}
