package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.books.GetBookByIDUsecas
import com.example.biblioteca.application.port.out.book.BookRepositoryPort
import com.example.biblioteca.domain.books.entities.Book
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetBookByIDService(private val repositoryPort: BookRepositoryPort): GetBookByIDUsecas {
    private val logger = org.slf4j.LoggerFactory.getLogger(javaClass)

    override fun execute(id: UUID): Book? {
        logger.info("Iniciando processo para obter livro pelo ID")

        logger.info("Livro obtido com sucesso!")

        return repositoryPort.getById(id)
    }
}