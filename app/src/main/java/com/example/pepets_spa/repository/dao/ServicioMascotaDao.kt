package com.example.pepets_spa.repository.dao

import androidx.room.*
import com.example.pepets_spa.model.ServicioMascota
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioMascotaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servicioMascota: ServicioMascota)

    @Update
    suspend fun update(servicioMascota: ServicioMascota)

    @Delete
    suspend fun delete(servicioMascota: ServicioMascota)

    @Query("SELECT * FROM servicios_mascotas")
    fun getAll(): Flow<List<ServicioMascota>>

    @Query("SELECT * FROM servicios_mascotas WHERE mascotaId = :mascotaId")
    fun getServiciosDeMascota(mascotaId: Int): Flow<List<ServicioMascota>>

    @Query("SELECT * FROM servicios_mascotas WHERE servicioId = :servicioId")
    fun getMascotasDeServicio(servicioId: Int): Flow<List<ServicioMascota>>

    @Query("SELECT * FROM servicios_mascotas WHERE id = :id LIMIT 1")
    fun getById(id: Int): Flow<ServicioMascota?>
}
