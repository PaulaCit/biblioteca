package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.UpdateBookCommand
import com.example.biblioteca.application.port.`in`.UpdateBookUsecase
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.books.entities.Book
import com.example.biblioteca.domain.books.exceptions.BookNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateBookService(private val repository: BookRepositoryPort ) : UpdateBookUsecase {

    private val logger = org.slf4j.LoggerFactory.getLogger(javaClass)

    @Transactional
    override fun execute(
       command: UpdateBookCommand

    ): Book {
        logger.info("Iniciando atualização do livro ${command.id}")

        val bookExistent = repository.getById(command.id) ?: run {
            logger.warn("Falha na atualização: Livro com ID ${command.id} não encontrado no banco.")
            throw BookNotFoundException(command.id)
        }

        return bookExistent.apply {
            updateDetails(command.title, command.isbn, command.author)
        }.let {
            logger.info("O livro foi  atualizado com sucesso!")
            repository.update(it) }
    }

}