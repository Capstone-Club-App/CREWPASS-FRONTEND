package com.example.crewpass_frontend.Retrofit.Personal.Application

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApplicationRetrofitInterfaces {
    // 지원서 작성하기
    @Multipart
    @POST("/application/new/{questionId}")
    fun postApplication(
        @Header("userId") userId: Int,
        @Path("questionId") questionId: Int,
        @Part("answer1") answer1 : String,
        @Part("answer2") answer2 : String,
        @Part("answer3") answer3 : String,
        @Part("answer4") answer4 : String?,
        @Part("answer5") answer5 : String?,
        @Part("answer6") answer6 : String?,
        @Part("answer7") answer7 : String?,
        @Part("answer1Count") answer1Count : Int,
        @Part("answer2Count") answer2Count : Int,
        @Part("answer3Count") answer3Count : Int,
        @Part("answer4Count") answer4Count : Int?,
        @Part("answer5Count") answer5Count : Int?,
        @Part("answer6Count") answer6Count : Int?,
        @Part("answer7Count") answer7Count : Int?,
    ): Call<ApplicationPostResponse>
}