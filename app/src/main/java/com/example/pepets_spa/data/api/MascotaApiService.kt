package com.example.pepets_spa.data.api


import com.example.pepets_spa.data.model.Mascota
import com.example.pepets_spa.data.model.SimpleResponse
import retrofit2.Response
import retrofit2.http.*

interface MascotaApiService {

    @GET("mascotas/cliente/{clienteId}")
    suspend fun getMascotas(@Path("clienteId") clienteId: Long): Response<List<Mascota>>

    @POST("mascotas")
    suspend fun crearMascota(@Body mascota: Mascota): Response<Mascota>

    @DELETE("mascotas/{id}")
    suspend fun eliminarMascota(@Path("id") mascotaId: Long): Response<SimpleResponse>
}
