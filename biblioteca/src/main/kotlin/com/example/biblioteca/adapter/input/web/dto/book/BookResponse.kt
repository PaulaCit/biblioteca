package com.example.biblioteca.adapter.input.web.dto.book

data class BookResponse(
    val id: String,
    val title: String,
    val author: String,
    val isbn: String,
    val isAvailable: Boolean,
)
