package com.example.pepets_spa.repository.dao

import androidx.room.*
import com.example.pepets_spa.model.Mascota
import kotlinx.coroutines.flow.Flow

@Dao
interface MascotaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mascota: Mascota)

    @Update
    suspend fun update(mascota: Mascota)

    @Delete
    suspend fun delete(mascota: Mascota)
    @Query("SELECT * FROM mascotas WHERE id = :id LIMIT 1")
    fun getMascotaById(id: Int): Flow<Mascota>


    @Query("SELECT * FROM mascotas")
    fun getAllMascotas(): Flow<List<Mascota>>

    @Query("SELECT * FROM mascotas WHERE propietarioId = :usuarioId")
    fun getMascotasDeUsuario(usuarioId: Int): Flow<List<Mascota>>

}
