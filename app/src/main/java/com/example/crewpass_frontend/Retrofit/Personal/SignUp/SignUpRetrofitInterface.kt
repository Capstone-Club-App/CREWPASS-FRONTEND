package com.example.crewpass_frontend.Retrofit.Personal.SignUp

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignUpRetrofitInterface {
    @Multipart
    @POST("/user/new")
    fun signUp (
        @Part("name") name: String,
        @Part("loginId") loginId : String,
        @Part("password") password : String,
        @Part("email") email : String,
        @Part("job") job : String,
        @Part("school") school : String?,
        @Part profile : MultipartBody.Part
    ): Call<SignUpResponse>
}