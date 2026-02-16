package com.example.biblioteca.application.port.`in`

import com.example.biblioteca.domain.entities.Book
import java.util.UUID

interface  GetBookByIDUsecas {
    fun execute(id: UUID): Book?
}