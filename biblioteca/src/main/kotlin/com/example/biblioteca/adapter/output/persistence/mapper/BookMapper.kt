package com.example.biblioteca.adapter.output.persistence.mapper

import com.example.biblioteca.adapter.output.persistence.entity.book.BookEntity
import com.example.biblioteca.domain.books.entities.Book
import com.example.biblioteca.domain.books.vo.Author
import com.example.biblioteca.domain.books.vo.Isbn
import com.example.biblioteca.domain.books.vo.Title

object BookMapper {


    fun toEntity(book: Book): BookEntity {
        return BookEntity(
            id = book.id,
            title = book.title.value,
            author = book.author.value,
            isbn = book.isbn.value,
            isAvailable = book.isAvailable
        )
    }

    fun toDomain(entity: BookEntity): Book {
        return Book(
            id = entity.id,
            title = Title(entity.title),
            author = Author(entity.author),
            isbn = Isbn(entity.isbn),
            isAvailable = entity.isAvailable
        )
    }
}