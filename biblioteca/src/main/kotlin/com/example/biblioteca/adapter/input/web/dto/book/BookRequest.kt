package com.example.biblioteca.adapter.input.web.dto.book

import com.example.biblioteca.application.port.`in`.books.RegisterBookCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/*
* Esse objeto define como o mundo externo fala com a gente. Ele protege o
* command interno
* */
data class BookRequest(
    @field:NotBlank(message = "O título é obrigatório")
    @field:Size(min =  3, message =  "O título deve ter no mínimo 3 caracteres")
    val title: String,

    @field:NotBlank(message = "O nome do autor é obrigatório")
    val author: String,

    @field:NotBlank(message = "O ISBN é obrigatório")
    val isbn: String,
){
    fun toCommand() = RegisterBookCommand(
        title = this.title,
        author = this.author,
        isbn = isbn,
    )
}
