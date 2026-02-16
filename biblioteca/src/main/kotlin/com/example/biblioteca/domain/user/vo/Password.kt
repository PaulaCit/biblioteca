package com.example.biblioteca.domain.user.vo

import com.example.biblioteca.domain.user.exceptions.InvalidPasswordException

@JvmInline
value class Password(val value: String) {
    init {
        // A senha criptografada (Hash) ou em texto plano não pode ser vazia
        // Assumindo um tamanho mínimo de 6 caracteres
        if (value.isBlank() || value.length < 6) {
            throw InvalidPasswordException()
        }
    }
}