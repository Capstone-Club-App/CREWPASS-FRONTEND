package com.example.crewpass_frontend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://34.64.142.47:8080"

fun getRetrofit(): Retrofit {
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    return retrofit
}