package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.GetBookByIDUsecas
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.entities.Book
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.math.log

@Service
class GetBookByIDService(private val repositoryPort: BookRepositoryPort): GetBookByIDUsecas {
    private val logger = org.slf4j.LoggerFactory.getLogger(javaClass)

    override fun execute(id: UUID): Book? {
        logger.info("Iniciando processo para obter livro pelo ID")

        logger.info("Livro obtido com sucesso!")

        return repositoryPort.getById(id)
    }
}