package com.example.biblioteca.application.service

import com.example.biblioteca.application.port.`in`.RegisterBookCommand
import com.example.biblioteca.application.port.`in`.RegisterBookUseCase
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.entities.Book
import com.example.biblioteca.domain.exceptions.IsbnAlreadyExistsException
import com.example.biblioteca.domain.vo.Author
import com.example.biblioteca.domain.vo.Isbn
import com.example.biblioteca.domain.vo.Title
import org.springframework.stereotype.Service

@Service // Indica ao Spring que esta classe contém a lógica de negócio (Application Service)
class RegisterBookService(private val repository: BookRepositoryPort) : RegisterBookUseCase {

    override fun execute(command: RegisterBookCommand): String {

        // 1. Regra da Aplicação (Fluxo)
        if(repository.existsByIsbn(command.isbn)){
            throw IsbnAlreadyExistsException(command.isbn)
        }

        // 2. Montagem da Entidade (Conversão String -> VO)
        // Aqui fica explícito que estamos transformando dados "crus" em "ricos"
        val newBook = Book(
            title = Title(command.title),
            isbn = Isbn(command.isbn),
            author = Author(command.author),
        )

        //3. Persistência
        repository.save(newBook)

        return newBook.id.toString()
    }
}