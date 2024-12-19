package com.klainus.web_api_labb_2.controller

import com.klainus.web_api_labb_2.model.CustomUser
import com.klainus.web_api_labb_2.model.LoginRequest
import com.klainus.web_api_labb_2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController @Autowired constructor(
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @GetMapping("/password")
    fun getBcryptPassword(): String {
        val testPassword = "123"
        return passwordEncoder.encode(testPassword)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<CustomUser>> {
        val users: List<CustomUser> = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @PostMapping
    fun saveUser(
        @Validated @RequestBody newUser: CustomUser
    ): ResponseEntity<String> {
        val bcryptUser = CustomUser(
            newUser.username,
            passwordEncoder.encode(newUser.password)
        )
        userRepository.save(bcryptUser)
        return ResponseEntity.status(201).body("newUser was created")
    }

    @PostMapping("/api/v1/users")
    fun loginUser(@Validated @RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val user = userRepository.findByUsername(loginRequest.username)
        return if (user != null && passwordEncoder.matches(loginRequest.password, user.password)) {
            ResponseEntity.ok("{\"message\": \"Login successful\"}")  // Correct JSON response
        } else {
            ResponseEntity.status(401).body("{\"message\": \"Invalid username or password\"}")
        }
    }

}
