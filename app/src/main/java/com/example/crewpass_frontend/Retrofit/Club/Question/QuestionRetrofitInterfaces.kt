package com.example.crewpass_frontend.Retrofit.Club.Question

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface QuestionRetrofitInterfaces {
    @Multipart
    @POST("/recruitment/new/question/new/{recruitmentId}")
    fun postQuestion(
        @Header("crewId") crewId : Int,
        @Path("recruitmentId") recruitment_id: Int,
        @Part("question1") question1: String,
        @Part("question2") question2: String,
        @Part("question3") question3: String,
        @Part("question4") question4: String?,
        @Part("question5") question5: String?,
        @Part("question6") question6: String?,
        @Part("question7") question7: String?,
        @Part("question1Limit") question1Limit: Int,
        @Part("question2Limit") question2Limit: Int,
        @Part("question3Limit") question3Limit: Int,
        @Part("question4Limit") question4Limit: Int?,
        @Part("question5Limit") question5Limit: Int?,
        @Part("question6Limit") question6Limit: Int?,
        @Part("question7Limit") question7Limit: Int?
    ): Call<QuestionPostResponse>
}