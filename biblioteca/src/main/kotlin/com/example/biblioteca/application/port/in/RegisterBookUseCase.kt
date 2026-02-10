package com.example.biblioteca.application.port.`in`

/*
* Define o que o sistema sabe fazer. É o contrtato que a Controller vai usar
* */

/*Command: Um DTO simples para transportar os dados da requisição (Controller)
para o caso de uso. É imutável e deve conter apenas dados primitivos.
* */
data class RegisterBookCommand(val title: String, val isbn: String, val author: String)

interface RegisterBookUseCase {
    fun execute(command: RegisterBookCommand): String // Retornar o ID do livro criado
}