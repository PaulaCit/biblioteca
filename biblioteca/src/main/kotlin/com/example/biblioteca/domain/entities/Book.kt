package com.example.biblioteca.domain.entities

import com.example.biblioteca.domain.exceptions.BorrowedBookException
import com.example.biblioteca.domain.vo.Author
import com.example.biblioteca.domain.vo.Isbn
import com.example.biblioteca.domain.vo.Title
import java.util.UUID

data class Book(val id: UUID = UUID.randomUUID(), private var isAvailable: Boolean = false){
    // Declaramos as propriedades aqui dentro para conseguir usar o private set
    var title: Title = Title("")
        private set

    var isbn: Isbn = Isbn("")
        private set

    var author: Author = Author("")
        private set

    // Caso queira inicializar via construtor, usamos um bloco init ou construtor secundário
    constructor(id: UUID, title: Title, isbn: Isbn, author: Author, isAvailable: Boolean) : this(id) {
        this.title = title
        this.isbn = isbn
        this.author = author
    }


    // Comportamentos --------

    // Emprestar
    fun borrow() {
       if (!isAvailable) throw BorrowedBookException()
       this.isAvailable = false
   }

    // Devolver
    fun toReturn(){
        this.isAvailable = true
    }

    // "Está disponível" (Check/Validação)
    fun checkAvailability(book: Book): Boolean = book.isAvailable


    fun updateDetails(newTitle: String?, newIsbn: String?, newAuthor: String?): Book {
        newTitle?.let { this.title = Title(it) }
        newIsbn?.let { this.isbn = Isbn(it) }
        newAuthor?.let { this.author = Author(it) }

        return this
    }
}