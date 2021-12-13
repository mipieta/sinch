package pl.mipieta.calculator.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.mipieta.calculator.security.JwtUtil

@RestController
internal class JwtController(@Autowired val jwtUtil: JwtUtil) {

    @PostMapping("/auth/token")
    fun generateTokenFor(@RequestBody username: String): String {
        return jwtUtil.buildJWT(username)
    }
}
