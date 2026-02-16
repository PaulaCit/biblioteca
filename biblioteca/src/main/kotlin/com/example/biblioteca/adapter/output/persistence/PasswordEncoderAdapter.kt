package com.example.biblioteca.adapter.output.persistence

import com.example.biblioteca.application.port.out.user.PasswordEncoderPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component // Transforma a classe num Bean para o Spring injetar no seu LoginService
class PasswordEncoderAdapter(
    // Injetamos a interface nativa do Spring Security
    private val springPasswordEncoder: PasswordEncoder
) : PasswordEncoderPort {

    override fun encode(rawPassword: String): String {
        // Pega a senha em texto plano (ex: "123456") e transforma no hash
        return springPasswordEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        // Compara a senha do login com o hash do banco de dados de forma segura
        return springPasswordEncoder.matches(rawPassword, encodedPassword)
    }
}