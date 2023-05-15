package com.example.crewpass_frontend.Retrofit.Club.Recruitment

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RecruitmentRetrofitInterfaces {
    @GET("/recruitment/myList")
    fun getRecruitment(@Header("crewId") crewId : Int): Call<RecruitmentResponse>

    @Multipart
    @POST("/recruitment/new")
    fun postRecruitment(
        @Part("title") title : String,
        @Part("isDeleted") isDeleted : Int,
        @Part("deadline") deadline : String,
        @Part("content") content : String,
                         @Part image : MultipartBody.Part, @Header("crewId") crewId : Int
    ): Call<RecruitmentPostResponse>
}