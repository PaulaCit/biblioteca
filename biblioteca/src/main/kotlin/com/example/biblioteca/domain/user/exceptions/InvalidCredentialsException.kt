package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class InvalidCredentialsException : DomainException("E-mail ou senha incorretos.")