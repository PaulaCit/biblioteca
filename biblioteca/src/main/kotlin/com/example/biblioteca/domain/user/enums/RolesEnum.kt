package com.example.biblioteca.domain.user.enums

enum class Role(val description: String) {
    USER("Usuário Padrão"),
    ADMIN("Administrador do Sistema"),
    LIBRARIAN("Bibliotecário")
}