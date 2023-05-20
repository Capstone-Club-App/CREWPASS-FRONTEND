package com.example.crewpass_frontend.Retrofit.Personal.Scrap

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ScrapRetrofitInterfaces {
    // 스크랩 등록
    @POST("/recruitment/scrap/new/{recruitmentId}")
    fun postScrap(@Path("recruitmentId") recruitmentId : Int,
                  @Header("userId") user_id: Int): Call<ScrapResponse>

    // 스크랩 삭제
    @DELETE("/recruitment/scrap/delete/{scrapId}")
    fun deleteScrap(@Path("scrapId") scrapId : Int, @Header("userId") user_id: Int): Call<ScrapDeleteResponse>

    // 스크랩 전체 가져오기
    @GET("/recruitment/scrap/{userId}")
    fun getScrap(@Path("userId") user_id : Int) : Call<ScrapGetAllResponse>
}

