package com.klainus.web_api_labb_2.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
class CustomUser (
    @field:NotEmpty
    @field:Size(min = 2, max = 20)
            val username: String,

    @field:NotEmpty
    @field:Size(min = 7, max = 64)
            val password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null)
