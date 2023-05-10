package com.example.crewpass_frontend.Retrofit.Club.LogIn

import com.example.crewpass_frontend.Retrofit.Club.SignUp.SignUpResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginRetrofitInterfaces {
    @POST("/crew/local")
    fun login (
        @Body data: Data
    ): Call<LoginResponse>
}