package com.example.biblioteca.domain.exceptions

class IsbnAlreadyExistsException(val isbn: String) : DomainException("Já existe um livro cadastrado com o ISBN $isbn") {
}