package com.example.biblioteca.application.port.`in`.books

import com.example.biblioteca.domain.books.entities.Book
import java.util.UUID

data class UpdateBookCommand(val id: UUID,  val title: String?, val isbn: String?, val author: String?)
interface  UpdateBookUsecase {
    fun execute(command: UpdateBookCommand): Book
}