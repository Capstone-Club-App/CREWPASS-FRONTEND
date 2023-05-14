package com.example.crewpass_frontend.Retrofit.Club.Recruitment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RecruitmentRetrofitInterfaces {
    @GET("/recruitment/myList")
    fun getRecruitment(@Header("crewId") crewId : Int): Call<RecruitmentResponse>
}