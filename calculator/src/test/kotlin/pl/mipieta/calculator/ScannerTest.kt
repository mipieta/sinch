package pl.mipieta.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.util.*

internal class ScannerTest {
    @Test
    fun `Finds successive operators from the right side`() {
        val scanner = Scanner(setOf("+", "*", "/", "-"))
        val tokens = LinkedList(listOf("-", "-1.5", "*", "3.1415", "/", "-7", "-2")) //7 elements

        assertAll(
            Executable { assertEquals(4, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 6)) },
            Executable { assertEquals(4, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 5)) },
            Executable { assertEquals(2, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 4)) },
            Executable { assertEquals(2, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 3)) },
            Executable { assertEquals(0, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 2)) },
            Executable { assertEquals(0, scanner.indexOfNextOperatorToTheLeftFrom(tokens, 1)) })
    }

    @Test
    fun `Returns -1 if there is no operator on the left side of the starting index`() {
        val scanner = Scanner(setOf("+", "*", "/", "-"))
        val validNotation = LinkedList(listOf("-", "-1.5", "*", "3.1415", "/", "-7", "-2")) //7 elements
        val invalidNotation = LinkedList(listOf("4", "-1.5", "*", "3.1415", "/", "-7", "-2")) //7 elements

        assertAll(
            Executable { assertEquals(-1, scanner.indexOfNextOperatorToTheLeftFrom(validNotation, 0)) },
            Executable { assertEquals(-1, scanner.indexOfNextOperatorToTheLeftFrom(invalidNotation, 2)) },
            Executable { assertEquals(-1, scanner.indexOfNextOperatorToTheLeftFrom(invalidNotation, 1)) },
            Executable { assertEquals(-1, scanner.indexOfNextOperatorToTheLeftFrom(invalidNotation, 0)) })
    }
}
