package com.example.biblioteca.application.service.user

import com.example.biblioteca.application.port.`in`.user.LoginUseCase
import com.example.biblioteca.application.port.`in`.user.command.LoginCommand
import com.example.biblioteca.application.port.`in`.user.response.TokenResponse
import com.example.biblioteca.application.port.out.user.PasswordEncoderPort
import com.example.biblioteca.application.port.out.user.TokenProviderPort
import com.example.biblioteca.application.port.out.user.UserRepositoryPort
import com.example.biblioteca.domain.user.exceptions.InvalidCredentialsException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepositoryPort,
    private val passwordEncoder: PasswordEncoderPort,
    private val tokenProvider: TokenProviderPort
) : LoginUseCase {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(command: LoginCommand): TokenResponse {
        logger.info("Iniciando tentativa de login para o e-mail: [{}]", command.email)

        // 1. Buscar o usuário pelo e-mail
        val user = userRepository.findByEmail(command.email)
            ?: run {
                // Logamos o real motivo internamente, mas lançamos a exceção genérica
                logger.warn("Falha no login: Usuário com e-mail [{}] não encontrado no banco de dados.", command.email)
                throw InvalidCredentialsException()
            }

        // 2. Verificar se a senha confere
        // command.password -> Senha "crua" que veio do JSON (ex: "minhasenha123")
        // user.password.value -> Hash criptografado que está no banco de dados (ex: "$2a$10$...")
        if (!passwordEncoder.matches(command.password, user.password.value)) {
            logger.warn("Falha no login: Senha incorreta fornecida para o e-mail [{}].", command.email)
            throw InvalidCredentialsException()
        }

        // 3. Gerar o Token
        logger.debug("Credenciais validadas com sucesso. Gerando JWT para o usuário ID [{}].", user.id)
        val token = tokenProvider.generateToken(user)

        logger.info("Login realizado com sucesso para o e-mail: [{}].", command.email)

        // 4. Retornar a resposta encapsulada
        return TokenResponse(
            accessToken = token,
            tokenType = "Bearer"
        )
    }
}