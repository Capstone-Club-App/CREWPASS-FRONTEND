package com.example.crewpass_frontend.Retrofit.Club.SignUp

import com.example.crewpass_frontend.Data.Crew
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("/crew/new")
    fun signUp (@Body crew : Crew): Call<SignUpResponse>
}