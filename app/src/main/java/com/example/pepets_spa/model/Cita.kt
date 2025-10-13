package com.example.pepets_spa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citas")
data class Cita(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuarioId: Int,
    val mascotaId: Int,
    val servicioId: Int,
    val fecha: String,        // Podría ser LocalDate con TypeConverter
    val hora: String,
    val estado: String = "pendiente"
)
