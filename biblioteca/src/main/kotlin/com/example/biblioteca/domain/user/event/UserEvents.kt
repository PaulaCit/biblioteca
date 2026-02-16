package com.example.biblioteca.domain.user.event

import com.example.biblioteca.domain.user.enums.Role
import java.util.UUID

// Interface de marcação para todos os eventos do nosso sistema
interface DomainEvent

data class UserProfileUpdatedEvent(
    val userId: UUID,
    val oldName: String,
    val newName: String,
    val oldEmail: String,
    val newEmail: String
) : DomainEvent

data class UserPasswordChangedEvent(val userId: UUID) : DomainEvent

data class UserRoleGrantedEvent(val userId: UUID, val role: Role) : DomainEvent

data class UserRoleRevokedEvent(val userId: UUID, val role: Role) : DomainEvent