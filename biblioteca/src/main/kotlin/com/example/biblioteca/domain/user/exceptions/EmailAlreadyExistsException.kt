package com.example.biblioteca.domain.user.exceptions

class EmailAlreadyExistsException(email: String) : RuntimeException("O e-mail '$email' já está cadastrado em nosso sistema.")