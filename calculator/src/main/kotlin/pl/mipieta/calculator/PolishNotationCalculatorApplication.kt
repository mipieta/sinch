package pl.mipieta.calculator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import pl.mipieta.calculator.operations.Operation

@SpringBootApplication
@Configuration
class PolishNotationCalculatorApplication(@Autowired val operations: List<Operation>) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val operationForOperator = mutableMapOf<String, Operation>()
        operations.associateByTo(operationForOperator) { it.operator() }

        val calculator = PolishNotationCalculator(operationForOperator)

        while (true) {
            val line = readLine() ?: break
            println(calculator.evaluate(line))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<PolishNotationCalculatorApplication>(*args)
}
