package com.example.biblioteca.adapter.input.web.dto

data class UpdateBookRequest(
    val title: String? = null,
    val isbn: String? = null,
    val author: String? = null
)
