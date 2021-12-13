package pl.mipieta.calculator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import pl.mipieta.calculator.operations.Operation
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@Configuration
@EnableSwagger2
@EnableWebSecurity
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

    @Bean
    internal fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("pl.mipieta.calculator"))
            .paths(PathSelectors.any())
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<PolishNotationCalculatorApplication>(*args)
}
