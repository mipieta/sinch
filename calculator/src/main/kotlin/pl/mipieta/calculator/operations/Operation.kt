package pl.mipieta.calculator.operations

import java.math.BigDecimal

interface Operation {
    fun operator(): String
    fun evaluate(a: BigDecimal, b: BigDecimal): BigDecimal
}
