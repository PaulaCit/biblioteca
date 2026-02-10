package com.example.biblioteca.domain.vo

import com.example.biblioteca.domain.exceptions.ShortTitleException
import com.example.biblioteca.domain.exceptions.TitleRequiredException

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