package com.example.biblioteca.adapter.output.persistence.security

import com.example.biblioteca.domain.entities.User

interface TokenProviderPort {

    fun generateToken(user: User): String
    fun validateToken(token: String): Boolean
    fun extractToken(token: String): String
}