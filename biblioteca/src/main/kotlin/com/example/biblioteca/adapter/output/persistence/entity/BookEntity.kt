package com.example.biblioteca.adapter.output.persistence.entity


import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

/*
* Esta classe representa como os dados são salvos no banco. Ela é diferente da Entidade de Domínio.
* Aqui usamos anotações do Hibernate(@Entity) e tipos primitivos, nada de VOs complexos.
* */

@Entity
@Table(name = "tb_book")
class BookEntity (
    @Id
    val id: UUID,
    val title: String,
    val author: String,
    val isbn: String,
    val isAvailable : Boolean){

    // O JPA exige um construtor vazio (pode ser protected)
    protected constructor(): this(UUID.randomUUID(), "", "", "", false)



}