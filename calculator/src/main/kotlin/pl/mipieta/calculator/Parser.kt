package pl.mipieta.calculator

import pl.mipieta.calculator.operations.Operation
import java.math.BigDecimal

internal class Parser(private val operationForOperator: Map<String, Operation>) {
    fun parse(slice: List<String>): Triple<Operation, BigDecimal, BigDecimal> {
        return Triple(
            operationForOperator.getValue(slice[0]),
            BigDecimal(slice[1]),
            BigDecimal(slice[2])
        )
    }
}
