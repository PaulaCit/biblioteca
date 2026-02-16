package com.example.biblioteca.domain.user.vo

import com.example.biblioteca.domain.user.exceptions.InvalidNameException

@JvmInline
value class Name(val value: String) {
    init {
        if (value.isBlank() || value.length < 3) {
            throw InvalidNameException()
        }
    }
}