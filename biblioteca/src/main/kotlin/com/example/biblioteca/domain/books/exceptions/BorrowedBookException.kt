package com.example.biblioteca.domain.books.exceptions

import com.example.biblioteca.domain.common.exceptions.DomainException

class BorrowedBookException : DomainException("Este livro j;a está emprestado e não pode ser reservado.")