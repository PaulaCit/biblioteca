package com.example.biblioteca.application.port.out

import com.example.biblioteca.domain.entities.Book

/*
* Interface de Saída (Repository Port)
* O Domínio/Aplicação dita as regras: "Eu preciso salvar um Livro".
* A infraestrutura (JPA/SQL) é quem vai obedecer e implementar isso depois
* */
interface BookRepositoryPort {
    fun save(book: Book): Book
    fun existsByIsbn(isbn: String): Boolean
}