package com.example.pepets_spa.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8081/"

    val api: ClienteApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClienteApiService::class.java)
    }
}
