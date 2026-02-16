package com.example.biblioteca.adapter.input.web.controller.user

import com.example.biblioteca.adapter.input.web.dto.user.LoginRequest
import com.example.biblioteca.adapter.input.web.dto.user.RegisterUserRequest
import com.example.biblioteca.application.port.`in`.user.LoginUseCase
import com.example.biblioteca.application.port.`in`.user.RegisterUserUseCase
import com.example.biblioteca.application.port.`in`.user.response.TokenResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/auth") // Uma rota separada só para autenticação
class AuthController(
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<TokenResponse> {

        // 1. Converte o DTO Web para o Command limpo
        val command = request.toCommand()

        // 2. Chama a regra de negócio e pega o Token
        val tokenResponse = loginUseCase.execute(command)

        // 3. Devolve 200 OK com o Token no corpo da resposta
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid request: RegisterUserRequest): ResponseEntity<Void> {
        val command = request.toCommand()
        val generatedId = registerUserUseCase.execute(command)

        // Retornar 201 Created com a URI do novo recurso no Header (Padrão RESTful)
        val location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/v1/users/{id}") // Caminho futuro caso você crie uma rota de buscar usuário
            .buildAndExpand(generatedId)
            .toUri()

        return ResponseEntity.created(location).build()
    }
}