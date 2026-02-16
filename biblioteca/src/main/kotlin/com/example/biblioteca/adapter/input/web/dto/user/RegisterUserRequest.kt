package com.example.biblioteca.adapter.input.web.dto.user

import com.example.biblioteca.application.port.`in`.user.command.RegisterUserCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterUserRequest(
    @field:NotBlank(message = "O nome é obrigatório")
    val name: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "Formato de e-mail inválido")
    val email: String,

    @field:NotBlank(message = "A senha é obrigatória")
    val password: String
) {
    fun toCommand(): RegisterUserCommand {
        return RegisterUserCommand(
            name = this.name,
            email = this.email,
            rawPassword = this.password
        )
    }
}