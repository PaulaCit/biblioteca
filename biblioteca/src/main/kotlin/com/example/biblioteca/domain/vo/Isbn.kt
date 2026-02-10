package com.example.biblioteca.domain.vo

import com.example.biblioteca.domain.exceptions.IsbnInvalidException

@JvmInline
value class Isbn(val value: String){
    init {
        if(!value.matches(Regex("^(97(8|9))?\\d{9}(\\d|X)$"))){
            throw IsbnInvalidException(value)
        }
    }
}