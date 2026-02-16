package com.example.biblioteca.application.port.`in`.user

import com.example.biblioteca.application.port.`in`.user.command.LoginCommand
import com.example.biblioteca.application.port.`in`.user.response.TokenResponse

interface LoginUseCase {

    // O UseCase tem apenas uma responsabilidade: Executar o login.
    // Se der erro (senha inválida, usuário não encontrado), ele deve lançar
    // Exceções de Domínio (ex: InvalidCredentialsException) que o
    // GlobalExceptionHandler vai capturar e transformar em erro 401 ou 403.
    fun execute(command: LoginCommand): TokenResponse

}