package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class IsbnInvalidException(isbn: String) : DomainException("o ISBN $isbn é inválido.")
