package pl.mipieta.calculator.operations

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
internal class Subtraction : Operation {
    override fun operator(): String = "-"

    override fun evaluate(a: BigDecimal, b: BigDecimal): BigDecimal {
        return BigDecimal("1.0")
    }
}