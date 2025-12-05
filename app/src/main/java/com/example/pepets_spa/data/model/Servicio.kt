package com.example.pepets_spa.data.model

data class Servicio(
    val id: Long? = null,
    val nombre: String,
    val descripcion: String?,
    val precio: Double?,
    val duracionMin: Int?,
    val imagenUrl: String?
)
