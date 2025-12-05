package com.example.pepets_spa.data.api

import com.example.pepets_spa.data.model.Cliente
import com.example.pepets_spa.data.model.LoginRequest
import com.example.pepets_spa.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClienteApiService {

    @POST("clientes/register")
    suspend fun registrar(@Body request: RegisterRequest): Response<Cliente>

    @POST("clientes/login")
    suspend fun login(@Body request: LoginRequest): Response<Cliente>
}
