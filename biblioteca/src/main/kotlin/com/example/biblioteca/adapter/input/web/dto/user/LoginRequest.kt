package com.example.biblioteca.adapter.input.web.dto.user

import com.example.biblioteca.application.port.`in`.user.command.LoginCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "Formato de e-mail inválido")
    val email: String,

    @field:NotBlank(message = "A senha é obrigatória")
    val password: String
) {
    // Método utilitário para converter o DTO da Web para o Command da Aplicação
    fun toCommand(): LoginCommand {
        return LoginCommand(
            email = this.email,
            password = this.password
        )
    }
}