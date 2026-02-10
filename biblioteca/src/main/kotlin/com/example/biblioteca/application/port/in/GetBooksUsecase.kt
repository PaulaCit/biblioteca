package com.example.biblioteca.application.port.`in`

import com.example.biblioteca.domain.entities.Book

interface
GetBooksUsecase {
    fun execute(): List<Book>
}