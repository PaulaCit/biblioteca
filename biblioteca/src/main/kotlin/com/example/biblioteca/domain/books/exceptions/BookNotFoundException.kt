package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException
import java.util.UUID

class BookNotFoundException(id: UUID) : DomainException("Livro não encontrado: $id") {
}