package pl.mipieta.calculator

import pl.mipieta.calculator.operations.Operation
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

internal class PolishNotationCalculator(
    private val parser: Parser,
    private val scanner: Scanner
) {
    fun evaluate(line: String, rethrow: Boolean = false): String {
        try {
            val tokens = LinkedList(line.split(' ')) //LinkedList because we need to remove from the middle

            //read from right to left
            var offset = tokens.lastIndex
            while (tokens.size >= 3 && offset >= 0) {
                offset = scanner.indexOfNextOperatorToTheLeftFrom(tokens, offset)
                //there will be two numbers directly on the right side of the operator
                val sliceForEvaluation = tokens.subList(offset, offset + 3)
                val expression = parser.parse(sliceForEvaluation)
                val result = evaluate(expression)
                sliceForEvaluation.clear() //removes three elements from the tokens list
                tokens.add(offset, result.toString())
            }

            if (tokens.size != 1) throw IllegalArgumentException("Not a valid Polish Notation expression")
            return BigDecimal(tokens.first).setScale(2, RoundingMode.HALF_UP).toString()
        } catch (e: RuntimeException) {
            if (rethrow) throw e
            return "error"
        }
    }

    private fun evaluate(expression: Triple<Operation, BigDecimal, BigDecimal>): BigDecimal {
        return expression.first.evaluate(expression.second, expression.third)
    }
}
