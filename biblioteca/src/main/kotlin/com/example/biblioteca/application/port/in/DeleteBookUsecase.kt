package com.example.biblioteca.application.port.`in`

import java.util.UUID

interface DeleteBookUsecase {

    fun execute(id: UUID)
}