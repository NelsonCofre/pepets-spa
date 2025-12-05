package com.example.pepets_spa.data.repository

import com.example.pepets_spa.data.api.CitaRetrofitClient
import com.example.pepets_spa.data.api.ClienteRetrofitClient
import com.example.pepets_spa.data.api.MascotaRetrofitClient
import com.example.pepets_spa.data.model.*

class AppRepository {

    private val clienteApi = ClienteRetrofitClient.api
    private val mascotaApi = MascotaRetrofitClient.api
    private val citaApi = CitaRetrofitClient.api

    // CLIENTE
    suspend fun login(email: String, pass: String) =
        clienteApi.login(LoginRequest(email, pass))

    suspend fun register(nombre: String, email: String, pass: String) =
        clienteApi.registrar(RegisterRequest(nombre, email, pass))


    // MASCOTAS
    suspend fun getMascotas(clienteId: Long) = mascotaApi.getMascotas(clienteId)

    suspend fun crearMascota(mascota: Mascota) = mascotaApi.crearMascota(mascota)

    suspend fun eliminarMascota(id: Long) = mascotaApi.eliminarMascota(id)


    // SERVICIOS / CITAS
    suspend fun getServicios() = citaApi.getServicios()

    suspend fun crearCita(cita: Cita) = citaApi.crearCita(cita)

    suspend fun getCitas(clienteId: Long) = citaApi.getCitas(clienteId)
}
