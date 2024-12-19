package com.klainus.web_api_labb_2.repository

import com.klainus.web_api_labb_2.model.CustomUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<CustomUser, Long> {
    fun findByUsername(username: String): CustomUser?
}
