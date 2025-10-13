package com.example.pepets_spa.repository

import com.example.pepets_spa.model.Servicio
import com.example.pepets_spa.repository.dao.ServicioDao

class ServicioRepository(private val servicioDao: ServicioDao) {

    suspend fun insertar(servicio: Servicio) = servicioDao.insert(servicio)
    suspend fun actualizar(servicio: Servicio) = servicioDao.update(servicio)
    suspend fun eliminar(servicio: Servicio) = servicioDao.delete(servicio)
    fun obtenerServicios() = servicioDao.getAllServicios()
    fun obtenerServicioPorId(id: Int) = servicioDao.getServicioById(id)
}
