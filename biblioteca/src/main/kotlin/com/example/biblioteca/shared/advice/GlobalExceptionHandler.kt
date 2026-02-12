package com.example.biblioteca.shared.advice

import com.example.biblioteca.adapter.input.web.dto.ApiErrorResponse
import com.example.biblioteca.domain.exceptions.DomainException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice // <-- Fundamental para funcionar!
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(DomainException::class)
    fun handleDomainException(e: DomainException, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {

        logger.warn("Erro de domínio: ${e.message}")

        val error = ApiErrorResponse(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            error = "Regra de negócio",
            message = e.message ?: "Erro desconhecido",
            path = request.requestURI,
            correlationId = MDC.get("correlationId")
        ) // O objeto termina aqui

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error)
    }

    // Dica: Adicione um handler genérico para capturar erros 500 inesperados
    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {
        logger.error("Erro interno no servidor: ${e.message}", e)

        val error = ApiErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Erro Interno",
            message = "Ocorreu um erro inesperado",
            path = request.requestURI,
            correlationId = MDC.get("correlationId")
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
    }
}