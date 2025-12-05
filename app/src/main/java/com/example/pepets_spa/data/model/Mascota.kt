package com.example.pepets_spa.data.model


data class Mascota(
    val id: Long? = null,
    val clienteId: Long,
    val nombre: String,
    val tipo: String,
    val edad: Int
)
