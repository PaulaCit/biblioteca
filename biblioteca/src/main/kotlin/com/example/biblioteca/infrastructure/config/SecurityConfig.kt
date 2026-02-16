package com.example.biblioteca.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // O BCrypt é o algoritmo mais recomendado para senhas hoje em dia.
        // Ele gera um 'salt' automático, garantindo que senhas iguais gerem hashes diferentes.
        return BCryptPasswordEncoder()
    }
}