package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class IsbnAlreadyExistsException(val isbn: String) : DomainException("Já existe um livro cadastrado com o ISBN $isbn") {
}