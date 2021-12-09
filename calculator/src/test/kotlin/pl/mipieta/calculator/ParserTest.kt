package pl.mipieta.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import pl.mipieta.calculator.operations.Addition
import java.math.BigDecimal

internal class ParserTest {
    @Test
    fun `Parsing a valid Polish Notation expression should return a triple of the operation and two numbers in the right order`() {
        val addition = Addition()
        val parsed = Parser(mapOf("+" to addition)).parse(listOf("+", "2", "3"))

        assertAll(Executable { assertEquals(addition, parsed.first) },
            Executable { assertEquals(BigDecimal(2), parsed.second) },
            Executable { assertEquals(BigDecimal(3), parsed.third) }
        )
    }

    @Test
    fun `Parser can handle scientific notation`() {
        val parser = Parser(mapOf("+" to Addition()))
        val parsed = parser.parse(listOf("+", "1e6", "2e10"))

        assertAll(
            Executable { assertEquals(0, BigDecimal(1_000_000).compareTo(parsed.second)) },
            Executable { assertEquals(0, BigDecimal(20_000_000_000).compareTo(parsed.third)) }
        )

    }

    @Test
    fun `Parsing an unknown operator should throw the NoSuchElementException`() {
        val parser = Parser(mapOf("+" to Addition()))

        assertThrows(NoSuchElementException::class.java) {
            parser.parse(listOf("*", "2", "3"))
        }
    }

    @Test
    fun `Parsing not-a-number should throw the NumberFormatException`() {
        val parser = Parser(mapOf("+" to Addition()))

        assertAll(
            Executable { assertThrows(NumberFormatException::class.java) { parser.parse(listOf("+", "2x", "3")) } },
            Executable { assertThrows(NumberFormatException::class.java) { parser.parse(listOf("+", "2", "3+")) } },
            Executable { assertThrows(NumberFormatException::class.java) { parser.parse(listOf("+", "+", "3")) } },
            Executable { assertThrows(NumberFormatException::class.java) { parser.parse(listOf("+", "1", "+")) } }
        )
    }
}
