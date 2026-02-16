package com.example.biblioteca.domain.exceptions

import java.util.UUID

class BookNotFoundException(id: UUID) : DomainException("Livro não encontrado: $id") {
}