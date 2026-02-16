package com.example.biblioteca.domain.books.entities

import com.example.biblioteca.domain.books.exceptions.BorrowedBookException
import com.example.biblioteca.domain.books.vo.Author
import com.example.biblioteca.domain.books.vo.Isbn
import com.example.biblioteca.domain.books.vo.Title
import java.util.UUID

class Book(
    val id: UUID = UUID.randomUUID(), // ID é a única coisa que nunca muda
    title: Title,
    isbn: Isbn,
    author: Author,
    isAvailable: Boolean = true // Um livro recém-criado costuma estar disponível
) {
    // 3. Atribuímos os parâmetros do construtor às propriedades da classe
    // Isso permite usar o 'private set' perfeitamente, garantindo o encapsulamento!
    var title: Title = title
        private set

    var isbn: Isbn = isbn
        private set

    var author: Author = author
        private set

    var isAvailable: Boolean = isAvailable
        private set

    // Comportamentos --------

    // Emprestar
    fun borrow() {
        if (!isAvailable) throw BorrowedBookException()
        this.isAvailable = false
    }

    // Devolver
    fun toReturn() {
        this.isAvailable = true
    }

    // "Está disponível" (Check/Validação)
    fun checkAvailability(): Boolean = this.isAvailable

    // Atualizar detalhes
    fun updateDetails(newTitle: String?, newIsbn: String?, newAuthor: String?): Book {
        newTitle?.let { this.title = Title(it) }
        newIsbn?.let { this.isbn = Isbn(it) }
        newAuthor?.let { this.author = Author(it) }
        return this
    }
}