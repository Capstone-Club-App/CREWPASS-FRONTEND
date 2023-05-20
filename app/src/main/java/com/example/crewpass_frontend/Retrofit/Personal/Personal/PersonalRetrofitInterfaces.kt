package com.example.crewpass_frontend.Retrofit.Personal.Personal

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

// 동아리 정보 가져오기
interface PersonalGetRetrofitInterfaces {
    @GET("/user")
    fun getPersonal(@Header("userId") userId: String): Call<PersonalResponse>
}

// 동아리 정보 수정
interface PersonalPutRetrofitInterfaces {
    @Multipart
    @PUT("/user")
    fun putPersonal(
        @Header("userId") userId: String,
        @Part("name") name: String,
        @Part("password") password: String,
        @Part("email") email: String,
        @Part("job") job: String,
        @Part("school") school: String,
        @Part profile: MultipartBody.Part,
        ): Call<PersonalPutResponse>
}