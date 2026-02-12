package com.example.biblioteca.adapter.input.web.dto

import java.sql.Timestamp
import java.time.LocalDateTime

data class ApiErrorResponse(val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
    val correlationId: String? = null,
    val field: Map<String, String>? = null
)