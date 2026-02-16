package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class InvalidEmailException(email: String) : DomainException("O formato do e-mail '$email' é inválido.")