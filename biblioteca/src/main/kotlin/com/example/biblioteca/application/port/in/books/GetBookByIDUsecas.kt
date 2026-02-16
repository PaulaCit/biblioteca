package com.example.biblioteca.application.port.`in`.books

import com.example.biblioteca.domain.books.entities.Book
import java.util.UUID

interface  GetBookByIDUsecas {
    fun execute(id: UUID): Book?
}