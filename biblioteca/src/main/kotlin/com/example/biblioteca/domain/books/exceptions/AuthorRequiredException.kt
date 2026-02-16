package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class AuthorRequiredException: DomainException("O Autor não pode ser vazio.")
