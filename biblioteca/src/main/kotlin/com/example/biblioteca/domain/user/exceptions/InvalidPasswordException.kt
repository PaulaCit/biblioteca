package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class InvalidPasswordException : DomainException("A senha deve conter pelo menos 6 caracteres.")