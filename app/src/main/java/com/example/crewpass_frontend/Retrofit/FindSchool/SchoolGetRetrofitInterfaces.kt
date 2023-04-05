package com.example.crewpass_frontend.Retrofit.FindSchool

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SchoolGetRetrofitInterfaces {
    @GET("getOpenApi")
    fun getSchool (@QueryMap querys : HashMap<String, String>): Call<SchoolGetResponse>
}