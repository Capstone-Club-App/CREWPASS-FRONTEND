package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecruitmentRetrofitInterfaces {
    // 모집글 전체 가져오기
    @GET("/recruitment/list/{field}/recent")
    fun getRecruitmentAll(@Path("field") field : String): Call<RecruitmentResponse>

    // 모집글 상세보기
    @GET("/recruitment/detail/{recruitmentId}")
    fun getRecruitmenDetail(@Path("recruitmentId") recruitmentId : Int): Call<RecruitmentDetailResponse>
}