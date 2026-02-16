package com.example.biblioteca.application.port.out.book

import com.example.biblioteca.domain.books.entities.Book
import java.util.UUID

/*
* Interface de Saída (Repository Port)
* O Domínio/Aplicação dita as regras: "Eu preciso salvar um Livro".
* A infraestrutura (JPA/SQL) é quem vai obedecer e implementar isso depois
* */
interface BookRepositoryPort {
    fun save(book: Book): Book
    fun existsByIsbn(isbn: String): Boolean

    fun getAll(): List<Book>

    fun update(book: Book): Book

    fun delete(id: UUID)

    fun getById(id: UUID): Book?
}