package com.example.pepets_spa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val email: String,
    val password: String,
    val telefono: String? = null,
    val direccion: String? = null
)
