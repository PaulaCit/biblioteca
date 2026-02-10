package com.example.biblioteca.adapter.output.persistence

import com.example.biblioteca.adapter.output.persistence.entity.BookEntity
import com.example.biblioteca.adapter.output.persistence.mapper.BookMapper
import com.example.biblioteca.adapter.output.persistence.repository.SpringDataBookRepository
import com.example.biblioteca.application.port.out.BookRepositoryPort
import com.example.biblioteca.domain.entities.Book
import com.example.biblioteca.domain.vo.Author
import com.example.biblioteca.domain.vo.Isbn
import com.example.biblioteca.domain.vo.Title
import org.springframework.stereotype.Component


@Component // Transforma essa classe num Boolena do Spring
class BookPersistenceAdapter(private val springRepository: SpringDataBookRepository) : BookRepositoryPort{
    override fun save(book: Book): Book {

        // 1. Converter Domínio -> Entidade JPA
       val entity = BookEntity(
           id = book.id,
           author = book.author.value, // Extraindo o valor primitivo do VO
           title = book.title.value,
           isbn = book.isbn.value,
           isAvailable = book.checkAvailability(book)
       )

        // 2. Salvar no banco
        val saved = springRepository.save(entity)


        // 3. Converter Entidade JPA -> Dominio (O retorno tem que ser Domínio!)
        return Book(saved.id,
            title = Title(saved.title),
            author = Author(saved.author),
            isbn = Isbn(saved.isbn),
            isAvailable = saved.isAvailable,
            )
    }

    override fun existsByIsbn(isbn: String): Boolean {
        return springRepository.existsByIsbn(isbn)
    }

    override fun getAll(): List<Book> {
        val entities = springRepository.findAll()
        return entities.map { entity ->
            BookMapper.toDomain(entity)
        }
    }
}