package com.example.biblioteca.application.port.`in`.user.response

// Retornamos um objeto estruturado em vez de apenas uma String solta.
// Isso facilita muito se no futuro quiser adicionar um "refreshToken" ou "expiresIn".
data class TokenResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
