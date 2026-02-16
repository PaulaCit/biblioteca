package com.example.biblioteca.domain.common.exceptions

// Classe base para todas as falhas da regra de negócio
abstract  class DomainException(message: String) : RuntimeException(message)