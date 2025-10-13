package com.example.pepets_spa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicios_mascotas")
data class ServicioMascota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mascotaId: Int,
    val servicioId: Int,
    val fecha: String,
    val estado: String = "pendiente"
)
