package com.example.crewpass_frontend.Retrofit.Club.AI

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AnalyzeApplicationRetrofitInterfaces {
    // 지원서 목록 가져오기
    @Multipart
    @POST("/ai/crew/interview")
    fun analyzeApplication(
        @Part("applicationId") applicationId: Int
    ): Call<AnalyzeApplicationResponse>

}