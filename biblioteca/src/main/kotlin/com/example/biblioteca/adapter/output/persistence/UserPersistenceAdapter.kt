package com.example.biblioteca.adapter.output.persistence

import com.example.biblioteca.adapter.output.persistence.mapper.user.UserMapper
import com.example.biblioteca.adapter.output.persistence.repository.user.SpringDataUserRepository
import com.example.biblioteca.application.port.out.user.UserRepositoryPort
import com.example.biblioteca.domain.user.entity.User
import org.springframework.stereotype.Component

@Component // Transforma a classe num Bean do Spring
class UserPersistenceAdapter(
    private val springRepository: SpringDataUserRepository
) : UserRepositoryPort {

    override fun findByEmail(email: String): User? {
        // 1. Busca a entidade do banco de dados (UserEntity)
        val entity = springRepository.findByEmail(email)

        // 2. Converte para a Entidade Rica de Domínio (User) caso exista
        return entity?.let { UserMapper.toDomain(it) }
    }

    override fun save(user: User): User {
        // 1. Converte o Domínio para Entidade do Banco
        val entity = UserMapper.toEntity(user)

        // 2. Salva no banco de dados
        val savedEntity = springRepository.save(entity)

        // 3. Retorna convertido para Domínio novamente
        return UserMapper.toDomain(savedEntity)
    }
}