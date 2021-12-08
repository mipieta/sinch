package pl.mipieta.calculator

import pl.mipieta.calculator.operations.Operation

internal class PolishNotationCalculator(private val operationsForOperators: Map<String, Operation>) {
    fun evaluate(line: String): String {
        return "foo bar"
    }
}