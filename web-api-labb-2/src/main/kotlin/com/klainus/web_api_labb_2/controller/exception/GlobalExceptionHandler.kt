package com.klainus.web_api_labb_2.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors: MutableMap<String, String> = mutableMapOf()

        // Collect validation errors from the exception
        for (fieldError in ex.bindingResult.fieldErrors) {
            errors[fieldError.field] = fieldError.defaultMessage ?: "Invalid value"
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}