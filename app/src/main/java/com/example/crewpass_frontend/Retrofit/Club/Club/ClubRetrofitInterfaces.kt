package com.example.crewpass_frontend.Retrofit.Club.Club

import retrofit2.Call
import retrofit2.http.GET

interface ClubRetrofitInterfaces {
    @GET("/crew")
    fun getClub(): Call<ClubResponse>
}