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
    @GET("/application/list/{questionId}")
    fun getApplication(
        @Path("questionId") questionId: Int
    ): Call<ApplicationGetResponse>

//    @Multipart
//    @POST("/notice")
//    fun postPassNpass(
//        @Part("crewName") crewName : String,
//        @Part("userId") userId : ArrayList<Int>,
//        @Part("msg") msg : String
//    ): Call<PassNpassResponse>

    @Multipart
    @POST("/notice/pass")
    fun postPass(
        @Part("recruitmentId") recruitmentId : Int,
        @Part("crewName") crewName : String,
        @Part("userId") userId : ArrayList<Int>,
        @Part("msg") msg : String
    ): Call<PassNpassResponse>

    @Multipart
    @POST("/notice/non-pass")
    fun postNpass(
        @Part("recruitmentId") recruitmentId : Int,
        @Part("crewName") crewName : String,
        @Part("userId") userId : ArrayList<Int>,
        @Part("msg") msg : String
    ): Call<PassNpassResponse>
}