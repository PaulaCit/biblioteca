package com.example.biblioteca.adapter.output.persistence.repository

import com.example.biblioteca.adapter.output.persistence.entity.book.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/*
* Aqui usamos a "mágica" do Spring para gera o SQL.
* */
interface SpringDataBookRepository: JpaRepository<BookEntity, UUID> {
    fun existsByIsbn(isbn: String): Boolean

}