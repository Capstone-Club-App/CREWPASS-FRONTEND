package com.example.crewpass_frontend.Retrofit.Club.Application

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApplicationRetrofitInterfaces {
    // 지원서 목록 가져오기
    @POST("/application/new/{questionId}")
    fun getApplication(
        @Header("crewId") crewId: Int,
        @Path("questionId") questionId: Int,
    ): Call<ApplicationGetResponse>

    @POST("/notice")
    fun postPassNpass(
        @Part("crewName") crewName : String,
        @Part("userId") userId : String,
        @Part("msg") msg : String
    ): Call<PassNpassResponse>
}