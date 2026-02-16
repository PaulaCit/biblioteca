package com.example.biblioteca.domain.user.vo

import com.example.biblioteca.domain.user.exceptions.InvalidEmailException

@JvmInline
value class Email(val value: String) {
    init {
        // Regex simples para validação de e-mail
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (value.isBlank() || !value.matches(emailRegex)) {
            throw InvalidEmailException(value)
        }
    }
}