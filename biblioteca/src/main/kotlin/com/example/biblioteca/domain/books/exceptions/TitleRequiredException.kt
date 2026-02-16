package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class TitleRequiredException : DomainException("O Título do livro não pode ser vazio.")
