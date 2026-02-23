package com.example.biblioteca.adapter.output.persistence.audit

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp

@Entity
@Table(name = "revinfo_custom")
@RevisionEntity(UserRevisionListener::class)
class CustomRevisionEntity {

    // 1. O Hibernate Envers vai usar isso como o ID da Revisão
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    var id: Int = 0

    // 2. O Hibernate Envers vai usar isso para gravar o Timestamp
    @RevisionTimestamp
    var timestamp: Long = 0

    // 3. O nosso campo customizado que o Listener vai preencher!
    var username: String? = null
}