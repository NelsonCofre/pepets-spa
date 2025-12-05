package com.example.pepets_spa.data.api

import com.example.pepets_spa.data.model.Cita
import com.example.pepets_spa.data.model.Servicio
import retrofit2.Response
import retrofit2.http.*

interface CitaApiService {

    @GET("servicios")
    suspend fun getServicios(): Response<List<Servicio>>

    @POST("citas")
    suspend fun crearCita(@Body cita: Cita): Response<Cita>

    @GET("citas/cliente/{clienteId}")
    suspend fun getCitas(@Path("clienteId") clienteId: Long): Response<List<Cita>>
}
