package com.example.biblioteca.adapter.output.persistence.entity.user

import jakarta.persistence.*
import org.hibernate.envers.Audited
import java.util.UUID

@Entity
@Audited
@Table(name = "users")
data class UserEntity(
    @Id
    val id: UUID,

    val name: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(name = "password_hash", nullable = false)
    val passwordHash: String,

    // Cria uma tabela auxiliar para guardar as Roles (USER, ADMIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role")
    val roles: Set<String> = setOf()
)