package com.klainus.web_api_labb_2.controller

import com.klainus.web_api_labb_2.model.CustomUser
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
    val passwordEncoder: PasswordEncoder
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
        @Validated @RequestBody newUser: CustomUser): ResponseEntity<String> {

       val bcryptUser = CustomUser(
            newUser.username,
            passwordEncoder.encode(newUser.password)
       )

        userRepository.save(bcryptUser)

        return ResponseEntity.status(201).body("newUser was created")
    }
}