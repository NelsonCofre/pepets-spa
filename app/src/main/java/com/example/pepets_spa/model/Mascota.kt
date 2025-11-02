package com.example.pepets_spa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascotas")
data class Mascota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val edad: Int,
    val especie: String,
    val raza: String? = null,
    val propietarioId: Int,
    val imageRes: Int?
)

