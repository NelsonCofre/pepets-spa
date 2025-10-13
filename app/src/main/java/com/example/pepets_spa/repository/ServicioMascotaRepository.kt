package com.example.pepets_spa.repository

import com.example.pepets_spa.model.ServicioMascota
import com.example.pepets_spa.repository.dao.ServicioMascotaDao
import kotlinx.coroutines.flow.Flow

class ServicioMascotaRepository(private val dao: ServicioMascotaDao) {

    suspend fun insertar(relacion: ServicioMascota) = dao.insert(relacion)
    suspend fun actualizar(relacion: ServicioMascota) = dao.update(relacion)
    suspend fun eliminar(relacion: ServicioMascota) = dao.delete(relacion)
    fun obtenerTodas(): Flow<List<ServicioMascota>> = dao.getAll()
    fun obtenerServiciosDeMascota(mascotaId: Int): Flow<List<ServicioMascota>> =
        dao.getServiciosDeMascota(mascotaId)
    fun obtenerMascotasDeServicio(servicioId: Int): Flow<List<ServicioMascota>> =
        dao.getMascotasDeServicio(servicioId)
    fun obtenerPorId(id: Int): Flow<ServicioMascota?> = dao.getById(id)
}
