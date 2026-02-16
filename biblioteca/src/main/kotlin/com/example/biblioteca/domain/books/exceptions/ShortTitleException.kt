package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class ShortTitleException : DomainException("Título muito curto.")
