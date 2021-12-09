package pl.mipieta.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import pl.mipieta.calculator.operations.Addition
import pl.mipieta.calculator.operations.Division
import pl.mipieta.calculator.operations.Multiplication
import pl.mipieta.calculator.operations.Subtraction

internal class PolishNotationCalculatorTest {
    @Test
    fun `Returns mathematically correct results with two precision digits for valid expressions `() {
        val calc = PolishNotationCalculator(
            Parser(mapOf("+" to Addition(), "-" to Subtraction(), "*" to Multiplication(), "/" to Division())),
            Scanner(setOf("+", "-", "*", "/"))
        )

        assertAll(
            Executable { assertEquals("42.00", calc.evaluate("+ + 0.5 1.5 * 4 10")) },
            Executable { assertEquals("1337.00", calc.evaluate("- 2e3 - 700 + 7 * 2 15")) },
            Executable { assertEquals("-12.50", calc.evaluate("- -1.5 * 3.1415 / -7 -2")) },
            Executable { assertEquals("100500.00", calc.evaluate("100500")) },
            Executable { assertEquals("5.00", calc.evaluate("- * / 15 - 7 + 1 1 3 + 2 + 1 1")) }
        )
    }

    @Test
    fun `Returns 'error' for invalid expressions `() {
        val calc = PolishNotationCalculator(
            Parser(mapOf("+" to Addition(), "-" to Subtraction(), "*" to Multiplication(), "/" to Division())),
            Scanner(setOf("+", "-", "*", "/"))
        )

        assertAll(
            Executable { assertEquals("error", calc.evaluate("1 2")) },
            Executable { assertEquals("error", calc.evaluate("+ 1")) },
            Executable { assertEquals("error", calc.evaluate("- -1.5 * 3.1415 ^ -7 -2")) }
        )
    }
}