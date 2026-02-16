package com.example.biblioteca.adapter.input.web.advice

import com.example.biblioteca.domain.common.exceptions.DomainException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

class GlobalExceptionHandler {

    @ExceptionHandler(DomainException::class)
    fun handleDomainException(ex: DomainException): ResponseEntity<Map<String, String>>{
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(mapOf("erro" to (ex.message ?: "Erro de regra de negócio")))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val erros = ex.bindingResult.fieldErrors.map {
            mapOf("campo" to it.field, "mensagem" to (it.defaultMessage ?: "inválido"))
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("errors" to erros))
    }
}