package com.example.biblioteca.application.port.`in`.user.command

data class RegisterUserCommand(
    val name: String,
    val email: String,
    val rawPassword: String // Senha pura que o usuário digitou
)