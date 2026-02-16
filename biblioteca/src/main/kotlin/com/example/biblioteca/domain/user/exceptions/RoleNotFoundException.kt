package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class RoleNotFoundException(role: String) : DomainException("O usuário não possui a permissão: $role")