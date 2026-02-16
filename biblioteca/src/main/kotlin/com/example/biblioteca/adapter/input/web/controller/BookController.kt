package com.example.biblioteca.adapter.input.web.controller

import com.example.biblioteca.adapter.input.web.dto.BookRequest
import com.example.biblioteca.adapter.input.web.dto.BookResponse
import com.example.biblioteca.adapter.input.web.dto.UpdateBookRequest
import com.example.biblioteca.application.port.`in`.DeleteBookUsecase
import com.example.biblioteca.application.port.`in`.GetBookByIDUsecas
import com.example.biblioteca.application.port.`in`.GetBooksUsecase
import com.example.biblioteca.application.port.`in`.RegisterBookUseCase
import com.example.biblioteca.application.port.`in`.UpdateBookCommand
import com.example.biblioteca.application.port.`in`.UpdateBookUsecase
import com.example.biblioteca.domain.books.exceptions.BookNotFoundException
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping( "api/v1/books")
class BookController(
    // Injeção da Porta de Entrada (Interface), não da implementação
    private val useCase: RegisterBookUseCase,
    private val getBooksUsecase: GetBooksUsecase,
    private val getBookByIDUsecas: GetBookByIDUsecas,
    private val updateBookUsecase: UpdateBookUsecase,
    private val deleteBookUsecase: DeleteBookUsecase
) {

    @GetMapping
    fun getBooks(): ResponseEntity<List<BookResponse>> {
        // 1. Chama o use case que retorna a lista do Domínio
        val books = getBooksUsecase.execute()

        // 2. Mapeia a lista de objetos de Domínio para a lista de DTOs de Resposta
        // (Ajuste os nomes dos campos conforme sua classe BookResponse)
        val response = books.map { book ->
            BookResponse(
                id = book.id.toString(),
                title = book.title.value,
                author = book.author.value,
                isbn = book.isbn.value,
                isAvailable = book.checkAvailability()
            )
        }

        // 3. Retorna Status 200 OK com a lista no corpo
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun registerBook(
        @RequestBody @Valid request: BookRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<String> {

        // 1. Converte DTO -> Command
        val command = request.toCommand()

        // 2. Chama a aplicação (Usecase)
        val idGerado = useCase.execute(command)

        // 3. monta a resposta (Header Location + Body)
        val uri =  uriBuilder.path("/books/{id}").buildAndExpand(idGerado).toUri()

        return ResponseEntity
            .created(uri)
            .body(idGerado)
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: UUID): ResponseEntity<BookResponse> {
        val book = getBookByIDUsecas.execute(id) ?: throw BookNotFoundException(id)

        val response = BookResponse(
            id = book.id.toString(),
            title = book.title.value,
            author = book.author.value,
            isbn = book.isbn.value,
            isAvailable = book.checkAvailability()
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UpdateBookRequest
    ): ResponseEntity<BookResponse> {

        // 1. Mapeia o Request para o Command do domínio
        val command = UpdateBookCommand(
            id = id,
            title = request.title,
            isbn = request.isbn,
            author = request.author
        )

        // 2. Executa a lógica
        val updatedBook = updateBookUsecase.execute(command)

        // 3. Mapeia para o Response (usando a lógica de segurança que discutimos)
        val response = BookResponse(
            id = updatedBook.id.toString(),
            title = updatedBook.title.value,
            author = updatedBook.author.value,
            isbn = updatedBook.isbn.value,
            isAvailable = updatedBook.checkAvailability()
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        val book = deleteBookUsecase.execute(id)

        return ResponseEntity.noContent().build()
    }

}