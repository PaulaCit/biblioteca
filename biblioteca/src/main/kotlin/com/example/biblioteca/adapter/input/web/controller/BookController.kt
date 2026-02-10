package com.example.biblioteca.adapter.input.web.controller

import com.example.biblioteca.adapter.input.web.dto.BookRequest
import com.example.biblioteca.adapter.input.web.dto.BookResponse
import com.example.biblioteca.application.port.`in`.RegisterBookUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping( "api/v1/books")
class BookController(
    // Injeção da Porta de Entrada (Interface), não da implementação
    private val useCase: RegisterBookUseCase
) {

    @PostMapping
    fun registerBook(
        @RequestBody @Valid request: BookRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<BookResponse> {

        // 1. Converte DTO -> Command
        val command = request.toCommand()

        // 2. Chama a aplicação (Usecase)
        val idGerado = useCase.execute(command)

        // 3. monta a resposta (Header Location + Body)
        val uri =  uriBuilder.path("/books/{id}").buildAndExpand(idGerado).toUri()

        return ResponseEntity
            .created(uri)
            .body(BookResponse(idGerado))
    }

}