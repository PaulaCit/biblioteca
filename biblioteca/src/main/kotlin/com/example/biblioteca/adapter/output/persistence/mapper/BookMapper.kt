package com.example.biblioteca.adapter.output.persistence.mapper

import com.example.biblioteca.adapter.output.persistence.entity.BookEntity
import com.example.biblioteca.domain.books.entities.Book
import com.example.biblioteca.domain.books.vo.Author
import com.example.biblioteca.domain.books.vo.Isbn
import com.example.biblioteca.domain.books.vo.Title

object BookMapper {

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