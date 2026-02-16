package com.example.biblioteca.application.service.book

import com.example.biblioteca.application.port.`in`.books.RegisterBookCommand
import com.example.biblioteca.application.port.`in`.books.RegisterBookUseCase
import com.example.biblioteca.application.port.out.book.BookRepositoryPort
import com.example.biblioteca.domain.books.entities.Book
import com.example.biblioteca.domain.books.exceptions.IsbnAlreadyExistsException
import com.example.biblioteca.domain.books.vo.Author
import com.example.biblioteca.domain.books.vo.Isbn
import com.example.biblioteca.domain.books.vo.Title
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service // Indica ao Spring que esta classe contém a lógica de negócio (Application Service)
class RegisterBookService(private val repository: BookRepositoryPort) : RegisterBookUseCase {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(command: RegisterBookCommand): String {
        logger.info("Iniciando cadastro de livro")

        // 1. Regra da Aplicação (Fluxo)
        if(repository.existsByIsbn(command.isbn)){
            logger.warn("Falha ao cadastrar livro. Livro já existe.")
            throw IsbnAlreadyExistsException(command.isbn)
        }

        // 2. Montagem da Entidade (Conversão String -> VO)
        val newBook = Book(
            id = UUID.randomUUID(),
            title = Title(command.title),
            isbn = Isbn(command.isbn),
            author = Author(command.author),
            isAvailable = true
        )

        //3. Persistência
        repository.save(newBook)

        logger.info("Livro cadastrado com sucesso!")

        return newBook.id.toString()
    }
}