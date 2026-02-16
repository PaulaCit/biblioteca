package com.example.biblioteca.application.port.`in`.user

import com.example.biblioteca.application.port.`in`.user.command.RegisterUserCommand
import java.util.UUID

interface RegisterUserUseCase {
    // Retornamos apenas o ID gerado (Padrão recomendadíssimo no DDD para comandos de criação)
    fun execute(command: RegisterUserCommand): UUID
}