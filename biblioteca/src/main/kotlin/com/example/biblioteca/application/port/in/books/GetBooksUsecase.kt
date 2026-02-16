package com.example.biblioteca.application.port.`in`.books

import com.example.biblioteca.domain.books.entities.Book

interface
GetBooksUsecase {
    fun execute(): List<Book>
}