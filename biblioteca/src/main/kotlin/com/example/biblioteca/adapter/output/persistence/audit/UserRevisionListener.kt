package com.example.biblioteca.adapter.output.persistence.audit

import org.hibernate.envers.RevisionListener
import org.springframework.security.core.context.SecurityContextHolder

class UserRevisionListener : RevisionListener {

    override fun newRevision(revisionEntity: Any) {
        val customRevisionEntity = revisionEntity as CustomRevisionEntity
        val authentication = SecurityContextHolder.getContext().authentication

        // Se tiver alguém logado no Spring Security, pegamos o nome/email dele
        if (authentication != null && authentication.isAuthenticated && authentication.principal != "anonymousUser") {
            customRevisionEntity.username = authentication.name
        } else {
            // Se for uma rotina automática do sistema (sem usuário na requisição)
            customRevisionEntity.username = "SISTEMA"
        }
    }
}