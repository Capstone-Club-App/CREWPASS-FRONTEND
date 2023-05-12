package com.example.crewpass_frontend.Retrofit.Club.LogIn

import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClubLoginRetrofitInterfaces {
    @Multipart
    @POST("/crew/local")
    fun login (
        @Part("loginId") loginId : String,
        @Part("password") password : String
    ): Call<LoginResponse>
}