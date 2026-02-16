package com.example.biblioteca.domain.books.vo

import com.example.biblioteca.domain.books.exceptions.AuthorRequiredException
import com.example.biblioteca.domain.books.exceptions.ShortAuthorException

@JvmInline
value class Author(val value: String){
    init {
        if (value.isBlank()) throw AuthorRequiredException()
        if(value.length < 2) throw ShortAuthorException()
    }
}