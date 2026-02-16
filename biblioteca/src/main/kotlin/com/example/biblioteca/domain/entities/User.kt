package com.example.biblioteca.domain.entities

import java.util.UUID

data class User(val id: UUID, val name: String, val email: String, val senha: String, val roles: Set<String> = setOf("USER"))