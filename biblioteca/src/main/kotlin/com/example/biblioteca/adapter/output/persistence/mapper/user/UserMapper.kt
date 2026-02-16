package com.example.biblioteca.adapter.output.persistence.mapper.user

import com.example.biblioteca.adapter.output.persistence.entity.user.UserEntity
import com.example.biblioteca.domain.user.entity.User
import com.example.biblioteca.domain.user.enums.Role
import com.example.biblioteca.domain.user.vo.Email
import com.example.biblioteca.domain.user.vo.Name
import com.example.biblioteca.domain.user.vo.Password

object UserMapper {

    // Banco de Dados -> Domínio
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            name = Name(entity.name),
            email = Email(entity.email),
            password = Password(entity.passwordHash),
            roles = entity.roles.map { Role.valueOf(it) }.toSet()
        )
    }

    // Domínio -> Banco de Dados
    fun toEntity(domain: User): UserEntity {
        return UserEntity(
            id = domain.id,
            name = domain.name.value,
            email = domain.email.value,
            passwordHash = domain.password.value,
            roles = domain.roles.map { it.name }.toSet()
        )
    }
}