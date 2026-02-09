package com.example.biblioteca.domain.exceptions

class IsbnInvalidException(isbn: String) : DomainException("o ISBN $isbn é inválido.")
