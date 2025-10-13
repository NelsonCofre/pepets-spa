package com.example.pepets_spa.repository.dao

import androidx.room.*
import com.example.pepets_spa.model.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servicio: Servicio)

    @Update
    suspend fun update(servicio: Servicio)

    @Delete
    suspend fun delete(servicio: Servicio)

    @Query("SELECT * FROM servicios")
    fun getAllServicios(): Flow<List<Servicio>>

    @Query("SELECT * FROM servicios WHERE id = :id LIMIT 1")
    fun getServicioById(id: Int): Flow<Servicio?>
}
