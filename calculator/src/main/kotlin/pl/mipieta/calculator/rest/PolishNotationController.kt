package pl.mipieta.calculator.rest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import pl.mipieta.calculator.PolishNotationCalculator

@RestController
internal class PolishNotationController(@Autowired val calculator: PolishNotationCalculator) {
    @PostMapping(
        path = ["/api/v1/calculators/polish-notation"],
        consumes = [MediaType.TEXT_PLAIN_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    @Operation(
        summary = "Evaluate a single expression in Polish Notation",
        description = "The Polish Notation expression calculator.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "A single line with a Polish Notation expression to be evaluated. Allowed arithmetic operators: [+, -, *, /]. Allowed numbers: double precision floating point (both positive and negative), scientific notation. Separator: single space-bar.",
            content = [Content(schema = Schema(example = "- 2e3 - 700 + 7 * 2 15"))],
            required = true
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "The result of the evaluation in double precision format.",
                content = [Content(examples = [ExampleObject("1337.00")])],
            ), ApiResponse(
                responseCode = "400",
                description = "The expression provided has invalid format."
            )]
    )

    fun evaluatePolishNotationExpression(@RequestBody expression: String): String {
        try {
            return calculator.evaluate(expression, true)
        } catch (e: RuntimeException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }
}
