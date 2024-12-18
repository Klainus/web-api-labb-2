package com.klainus.web_api_labb_2.repository

import com.klainus.web_api_labb_2.model.CustomUser
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<CustomUser, Long> {
}