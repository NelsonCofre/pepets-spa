package com.example.pepets_spa.repository

import com.example.pepets_spa.model.Usuario
import com.example.pepets_spa.repository.dao.UsuarioDao

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun insertar(usuario: Usuario) = usuarioDao.insert(usuario)

    suspend fun actualizar(usuario: Usuario) = usuarioDao.update(usuario)

    suspend fun eliminar(usuario: Usuario) = usuarioDao.delete(usuario)

    fun obtenerUsuarios() = usuarioDao.getAllUsuarios()

    suspend fun obtenerUsuarioPorEmail(email: String): Usuario? =
        usuarioDao.getUsuarioByEmail(email)
}
