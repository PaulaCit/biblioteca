package com.example.biblioteca.application.service.user

import com.example.biblioteca.application.port.`in`.user.RegisterUserUseCase
import com.example.biblioteca.application.port.`in`.user.command.RegisterUserCommand
import com.example.biblioteca.application.port.out.user.PasswordEncoderPort
import com.example.biblioteca.application.port.out.user.UserRepositoryPort
import com.example.biblioteca.domain.user.entity.User
import com.example.biblioteca.domain.user.exceptions.EmailAlreadyExistsException
import com.example.biblioteca.domain.user.vo.Email
import com.example.biblioteca.domain.user.vo.Name
import com.example.biblioteca.domain.user.vo.Password
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RegisterUserService(
    private val userRepository: UserRepositoryPort,
    private val passwordEncoder: PasswordEncoderPort
) : RegisterUserUseCase {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(command: RegisterUserCommand): UUID {
        logger.info("Iniciando cadastro para o novo usuário com e-mail: [{}]", command.email)

        // 1. Regra de Negócio: E-mail deve ser único
        if (userRepository.findByEmail(command.email) != null) {
            logger.warn("Tentativa de cadastro falhou. O e-mail [{}] já existe.", command.email)
            throw EmailAlreadyExistsException(command.email)
        }

        // 2. Segurança: Criptografar a senha ANTES de instanciar a Entidade
        val hashedPassword = passwordEncoder.encode(command.rawPassword)

        // 3. Criação da Entidade Rica
        // OBS: Se o e-mail for inválido ou o nome for curto, os Value Objects
        // vão lançar exceções aqui mesmo e interromper o fluxo! Mágica do DDD.
        val newUser = User(
            name = Name(command.name),
            email = Email(command.email),
            password = Password(hashedPassword)
            // Não passamos as "roles", então ele assume a padrão (Role.USER) que definimos na classe User
        )

        // 4. Salvar no Banco de Dados
        val savedUser = userRepository.save(newUser)

        logger.info("Usuário cadastrado com sucesso! ID gerado: [{}]", savedUser.id)

        // 5. Retorna a Identidade gerada
        return savedUser.id
    }
}