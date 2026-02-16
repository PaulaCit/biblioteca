package com.example.biblioteca.application.port.out.user

import com.example.biblioteca.domain.user.entity.User

interface TokenProviderPort {

    fun generateToken(user: User): String
    fun validateToken(token: String): Boolean
    fun extractToken(token: String): String
}