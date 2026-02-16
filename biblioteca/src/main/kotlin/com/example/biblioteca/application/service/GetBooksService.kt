package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.GetBooksUsecase
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.books.entities.Book
import org.springframework.stereotype.Service

@Service
class GetBooksService(private val repositoryPort: BookRepositoryPort) : GetBooksUsecase {
    override fun execute(): List<Book> {
        return repositoryPort.getAll()
    }
}