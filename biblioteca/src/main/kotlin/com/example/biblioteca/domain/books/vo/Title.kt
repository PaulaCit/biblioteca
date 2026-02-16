package com.example.biblioteca.domain.books.vo

import com.example.biblioteca.domain.books.exceptions.ShortTitleException
import com.example.biblioteca.domain.books.exceptions.TitleRequiredException

@JvmInline
value class Title(val value: String){
    init {
        if (value.isBlank()){throw TitleRequiredException()
        }
        if(value.length < 3){
            throw ShortTitleException()
        }
    }
}