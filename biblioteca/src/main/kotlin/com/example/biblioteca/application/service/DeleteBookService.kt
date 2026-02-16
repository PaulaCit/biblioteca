package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.DeleteBookUsecase
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.exceptions.BookNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteBookService(private val repositoryPort: BookRepositoryPort): DeleteBookUsecase {
    private val logger = org.slf4j.LoggerFactory.getLogger(javaClass)

    override fun execute(id: UUID) {
        logger.info("Iniciando processo de deletar livro ${id}")

        val existentBook = repositoryPort.getById(id) ?: run{
            logger.warn("Livro ${id} não encontrado")
            throw BookNotFoundException(id)
        }

        return repositoryPort.delete(id)
    }
}