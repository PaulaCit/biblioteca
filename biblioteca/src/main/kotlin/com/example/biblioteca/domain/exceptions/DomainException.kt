package com.example.biblioteca.domain.exceptions

// Classe base para todas as falhas da regra de negócio
abstract  class DomainException(message: String) : RuntimeException(message)
