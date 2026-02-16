package com.example.biblioteca.domain.user.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class InvalidNameException : DomainException("O nome do usuário não pode ser vazio ou muito curto.")