package com.example.biblioteca.adapter.output.persistence.entity.book

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

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