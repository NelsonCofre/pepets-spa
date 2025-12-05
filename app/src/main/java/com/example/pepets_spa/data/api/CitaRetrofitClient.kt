package com.example.pepets_spa.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CitaRetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8085/"

    val api: CitaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CitaApiService::class.java)
    }
}
