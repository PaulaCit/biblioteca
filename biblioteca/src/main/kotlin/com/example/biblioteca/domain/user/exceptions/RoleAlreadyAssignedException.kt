package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class RoleAlreadyAssignedException(role: String) : DomainException("O usuário já possui a permissão: $role")