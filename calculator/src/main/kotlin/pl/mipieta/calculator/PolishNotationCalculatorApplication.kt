package pl.mipieta.calculator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.mipieta.calculator.operations.Operation

@SpringBootApplication
@Configuration
class PolishNotationCalculatorApplication(@Autowired val operations: List<Operation>) : CommandLineRunner {
    private val operationForOperator: Map<String, Operation>

    init {
        operationForOperator = mutableMapOf()
        operations.associateByTo(operationForOperator) { it.operator() }
    }

    override fun run(vararg args: String?) {
        while (true) {
            val line = readLine() ?: break
            println(calculator().evaluate(line))
        }
    }

    @Bean
    internal fun calculator(): PolishNotationCalculator {
        return PolishNotationCalculator(parser(), scanner())
    }

    @Bean
    internal fun parser(): Parser {
        return Parser(operationForOperator)
    }

    @Bean
    internal fun scanner(): Scanner {
        return Scanner(operationForOperator.keys)
    }
}

fun main(args: Array<String>) {
    runApplication<PolishNotationCalculatorApplication>(*args)
}
