package com.example.crewpass_frontend.Retrofit.Personal.Application

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class ApplicationPostResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
    )


// 질문 불러오기
data class QuestionGetResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : QuestionData
)

data class QuestionData(
    @SerializedName("question1") val question1 : String,
    @SerializedName("question2") val question2 : String,
    @SerializedName("question3") val question3 : String,
    @SerializedName("question4") val question4 : String?,
    @SerializedName("question5") val question5 : String?,
    @SerializedName("question6") val question6 : String?,
    @SerializedName("question7") val question7 : String?,
    @SerializedName("question1Limit") val question1Limit : Int,
    @SerializedName("question2Limit") val question2Limit : Int,
    @SerializedName("question3Limit") val question3Limit : Int,
    @SerializedName("question4Limit") val question4Limit : Int?,
    @SerializedName("question5Limit") val question5Limit : Int?,
    @SerializedName("question6Limit") val question6Limit : Int?,
    @SerializedName("question7Limit") val question7Limit : Int?,
    @SerializedName("recruitmentId") val recruitmentId : Int
)

// 지원서 상세 가져오기
data class ApplicationGetResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<ApplicationData>
)

data class ApplicationData(
    @SerializedName("user_name") val user_name : String,
    @SerializedName("user_profile") val user_profile : String,
    @SerializedName("submit_time") val submit_time : Timestamp,
    @SerializedName("question1") val question1 : String,
    @SerializedName("question2") val question2 : String,
    @SerializedName("question3") val question3 : String,
    @SerializedName("question4") val question4 : String?,
    @SerializedName("question5") val question5 : String?,
    @SerializedName("question6") val question6 : String?,
    @SerializedName("question7") val question7 : String?,
    @SerializedName("question1_limit") val question1Limit : Int,
    @SerializedName("question2_limit") val question2Limit : Int,
    @SerializedName("question3_limit") val question3Limit : Int,
    @SerializedName("question4_limit") val question4Limit : Int?,
    @SerializedName("question5_limit") val question5Limit : Int?,
    @SerializedName("question6_limit") val question6Limit : Int?,
    @SerializedName("question7_limit") val question7Limit : Int?,
    @SerializedName("answer1") val answer1 : String,
    @SerializedName("answer2") val answer2 : String,
    @SerializedName("answer3") val answer3 : String,
    @SerializedName("answer4") val answer4 : String?,
    @SerializedName("answer5") val answer5 : String?,
    @SerializedName("answer6") val answer6 : String?,
    @SerializedName("answer7") val answer7 : String?,
    @SerializedName("answer1_count") val answer1Count : Int,
    @SerializedName("answer2_count") val answer2Count : Int,
    @SerializedName("answer3_count") val answer3Count : Int,
    @SerializedName("answer4_count") val answer4Count : Int?,
    @SerializedName("answer5_count") val answer5Count : Int?,
    @SerializedName("answer6_count") val answer6Count : Int?,
    @SerializedName("answer7_count") val answer7Count : Int?
)

// 지원서 리스트 가져오기
data class ApplicationGetListResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<Application>
)

data class Application(
    @SerializedName("user_user_id") val user_user_id : Int,
    @SerializedName("crew_name") val crew_name : String,
    @SerializedName("application_id") val application_id : Int,
    @SerializedName("submit_time") val submit_time : Timestamp,
    @SerializedName("crew_profile") val crew_profile : String?
)