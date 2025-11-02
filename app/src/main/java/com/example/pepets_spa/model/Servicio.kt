package com.example.pepets_spa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicios")
data class Servicio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val tipo: String,
    val duracion: Int

)
