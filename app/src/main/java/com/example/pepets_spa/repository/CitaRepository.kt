package com.example.pepets_spa.repository

import com.example.pepets_spa.model.Cita
import com.example.pepets_spa.repository.dao.CitaDao

class CitaRepository(private val citaDao: CitaDao) {

    suspend fun insertar(cita: Cita) = citaDao.insert(cita)
    suspend fun actualizar(cita: Cita) = citaDao.update(cita)
    suspend fun eliminar(cita: Cita) = citaDao.delete(cita)
    fun obtenerCitas() = citaDao.getAllCitas()
    fun obtenerCitaPorId(id: Int) = citaDao.getCitaById(id)

    fun obtenerCitasDeUsuario(usuarioId: Int) = citaDao.getCitasDeUsuario(usuarioId)

}
