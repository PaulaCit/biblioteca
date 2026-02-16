package com.example.biblioteca.application.port.out.user

import com.example.biblioteca.domain.user.entity.User

interface UserRepositoryPort {
    fun findByEmail(email: String): User?
    fun save(user: User): User
}