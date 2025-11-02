package com.example.pepets_spa.repository.dao

import androidx.room.*
import com.example.pepets_spa.model.Cita
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {

    @Query("SELECT * FROM citas")
    fun getAllCitas(): Flow<List<Cita>>

    @Query("SELECT * FROM citas WHERE id = :id LIMIT 1")
    fun getCitaById(id: Int): Flow<Cita?>

    @Query("SELECT * FROM citas WHERE usuarioId = :usuarioId")
    fun getCitasDeUsuario(usuarioId: Int): Flow<List<Cita>>

    @Insert
    suspend fun insert(cita: Cita)

    @Update
    suspend fun update(cita: Cita)

    @Delete
    suspend fun delete(cita: Cita)
}

