package com.example.pepets_spa.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pepets_spa.model.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    fun getAllUsuarios(): LiveData<List<Usuario>>

    // 👇 Importante: que devuelva Usuario?, no LiveData<Usuario?>
    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getUsuarioByEmail(email: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun obtenerPorEmail(email: String): Usuario?

}
