package com.example.biblioteca.domain.vo

import com.example.biblioteca.domain.exceptions.AuthorRequiredException
import com.example.biblioteca.domain.exceptions.ShortAuthorException

@JvmInline
value class Author(val value: String){
    init {
        if (value.isBlank()) throw AuthorRequiredException()
        if(value.length < 2) throw ShortAuthorException()
    }
}