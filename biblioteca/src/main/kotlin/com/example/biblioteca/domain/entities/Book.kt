package com.example.biblioteca.domain.entities

import com.example.biblioteca.domain.exceptions.BorrowedBookException
import com.example.biblioteca.domain.vo.Author
import com.example.biblioteca.domain.vo.Isbn
import com.example.biblioteca.domain.vo.Title
import java.util.UUID

data class Book(val id: UUID = UUID.randomUUID(), val title: Title, val author: Author, val isbn: Isbn, private var isAvailable: Boolean){

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
}