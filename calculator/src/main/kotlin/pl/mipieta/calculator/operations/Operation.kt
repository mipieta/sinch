package pl.mipieta.calculator.operations

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
interface Operation {
    fun operator(): String
    fun evaluate(a: BigDecimal, b: BigDecimal): BigDecimal
}
