package com.example.pepets_spa.data.model


data class Cita(
    val id: Long? = null,
    val clienteId: Long,
    val mascotaId: Long,
    val servicioId: Long,
    val fecha: String,
    val hora: String,
    val estado: String
)
