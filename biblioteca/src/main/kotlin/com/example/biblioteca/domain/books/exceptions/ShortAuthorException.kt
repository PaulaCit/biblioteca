package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class ShortAuthorException : DomainException("Autor precisa ter no mínimo 3 letras")
