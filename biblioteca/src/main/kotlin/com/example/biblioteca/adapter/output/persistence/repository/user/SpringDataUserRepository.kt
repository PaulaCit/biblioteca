package com.example.biblioteca.adapter.output.persistence.repository.user

import com.example.biblioteca.adapter.output.persistence.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringDataUserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}