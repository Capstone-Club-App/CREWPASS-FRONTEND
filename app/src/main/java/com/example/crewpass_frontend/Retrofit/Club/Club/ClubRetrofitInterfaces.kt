package com.example.crewpass_frontend.Retrofit.Club.Club

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

// 동아리 정보 가져오기
interface ClubGetRetrofitInterfaces {
    @GET("/crew")
    fun getClub(@Header("crewId") crewId: Int): Call<ClubResponse>
}

// 동아리 정보 수정
interface ClubPutRetrofitInterfaces {
    @Multipart
    @PUT("/crew")
    fun putClub(
        @Header("crewId") crewId: Int,
        @Part("name") name: String,
        @Part("password") password: String,
        @Part("region1") region1: String,
        @Part("region2") region2: String,
        @Part("field1") field1: String,
        @Part("field2") field2: String,
        @Part("masterEmail") masterEmail: String,
        @Part("subEmail") subEmail: String,
        @Part profile: MultipartBody.Part,
        ): Call<ClubPutResponse>
}