package com.example.biblioteca.application.port.`in`.books

import java.util.UUID

interface DeleteBookUsecase {

    fun execute(id: UUID)
}