package com.example.pepets_spa.repository

import com.example.pepets_spa.model.Mascota
import com.example.pepets_spa.repository.dao.MascotaDao

class MascotaRepository(private val mascotaDao: MascotaDao) {

    suspend fun insertar(mascota: Mascota) = mascotaDao.insert(mascota)

    suspend fun actualizar(mascota: Mascota) = mascotaDao.update(mascota)

    suspend fun eliminar(mascota: Mascota) = mascotaDao.delete(mascota)

    fun obtenerMascotas() = mascotaDao.getAllMascotas()

    fun obtenerMascotaPorId(id: Int) = mascotaDao.getMascotaById(id)
}
