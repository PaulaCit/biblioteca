package com.example.biblioteca.domain.user.entity

import com.example.biblioteca.domain.user.enums.Role
import com.example.biblioteca.domain.user.event.*
import com.example.biblioteca.domain.user.exceptions.RoleAlreadyAssignedException
import com.example.biblioteca.domain.user.exceptions.RoleNotFoundException
import com.example.biblioteca.domain.user.vo.Email
import com.example.biblioteca.domain.user.vo.Name
import com.example.biblioteca.domain.user.vo.Password
import java.util.UUID

 class User(
    val id: UUID = UUID.randomUUID(),
    name: Name,
    email: Email,
    password: Password,
    roles: Set<Role> = setOf(Role.USER)
) {
    // 1. O Logger Técnico
    private val logger = org.slf4j.LoggerFactory.getLogger(javaClass)

    // 2. A Lista de Eventos de Domínio (Rastreabilidade de Negócio)
    private val _domainEvents = mutableListOf<DomainEvent>()
    val domainEvents: List<DomainEvent>
        get() = _domainEvents.toList()

    // Método para a infraestrutura limpar os eventos após publicá-los
    fun clearDomainEvents() {
        _domainEvents.clear()
    }

    var name: Name = name
        private set

    var email: Email = email
        private set

    var password: Password = password
        private set

    private val _roles: MutableSet<Role> = roles.toMutableSet()
    val roles: Set<Role>
        get() = _roles.toSet()

    // --- COMPORTAMENTOS (Regras de Negócio com Rastreabilidade) ---

    fun updateProfile(newName: String?, newEmail: String?) {
        val oldName = this.name.value
        val oldEmail = this.email.value

        newName?.let { this.name = Name(it) }
        newEmail?.let { this.email = Email(it) }

        val updatedName = this.name.value
        val updatedEmail = this.email.value

        // Só geramos log e evento se realmente houve mudança
        if (oldName != updatedName || oldEmail != updatedEmail) {
            logger.info("Usuário [{}] alterou o perfil. Nome: [{} -> {}], Email: [{} -> {}]",
                id, oldName, updatedName, oldEmail, updatedEmail)

            _domainEvents.add(
                UserProfileUpdatedEvent(id, oldName, updatedName, oldEmail, updatedEmail)
            )
        }
    }

    fun changePassword(newPassword: String) {
        logger.info("Usuário [{}] solicitou alteração de senha.", id)

        this.password = Password(newPassword)

        _domainEvents.add(UserPasswordChangedEvent(id))
        logger.debug("Senha do usuário [{}] alterada com sucesso.", id) // debug para não poluir o console principal
    }

    fun grantRole(role: Role) {
        if (_roles.contains(role)) {
            logger.warn("Tentativa falha de conceder permissão. Usuário [{}] já possui a role [{}].", id, role)
            throw RoleAlreadyAssignedException(role.name)
        }

        _roles.add(role)

        logger.info("Permissão [{}] concedida ao usuário [{}].", role, id)
        _domainEvents.add(UserRoleGrantedEvent(id, role))
    }

    fun revokeRole(role: Role) {
        if (!_roles.contains(role)) {
            logger.warn("Tentativa falha de revogar permissão. Usuário [{}] não possui a role [{}].", id, role)
            throw RoleNotFoundException(role.name)
        }

        _roles.remove(role)

        logger.info("Permissão [{}] revogada do usuário [{}].", role, id)
        _domainEvents.add(UserRoleRevokedEvent(id, role))
    }

     override fun equals(other: Any?): Boolean {
         if (this === other) return true
         if (javaClass != other?.javaClass) return false
         other as User
         return id == other.id
     }

     override fun hashCode(): Int {
         return id.hashCode()
     }
}