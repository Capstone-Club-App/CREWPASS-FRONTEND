package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.sql.Timestamp

// 모집글 전체 최신순으로
data class RecruitmentResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<Recruitment>
)

data class Recruitment(
    @SerializedName(value = "crew_id") val crew_id : Int,
    @SerializedName(value = "recruitment_id") val recruitment_id : Int,
    @SerializedName(value = "question_id") val question_id : Int,
    @SerializedName(value = "register_time") val register_time : Timestamp,
    @SerializedName(value = "crew_profile") val crew_profile : String,
    @SerializedName(value = "crew_name") val crew_name : String,
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "field2") val field2 : String,
    @SerializedName(value = "field1") val field1 : String,
    @SerializedName(value = "deadline") val deadline : Timestamp,
    @SerializedName(value = "region2") val region2 : String,
    @SerializedName(value = "region1") val region1 : String,
    @SerializedName(value = "d_day") val d_day : Int
)

//data class Recruitment(
//    @SerializedName(value = "title") val title : String,
//    @SerializedName(value = "region1") val region1 : String,
//    @SerializedName(value = "region2") val region2 : String,
//    @SerializedName(value = "field1") val field1 : String,
//    @SerializedName(value = "field2") val field2 : String,
//    @SerializedName(value = "crew_profile") val crew_profile : String,
//    @SerializedName(value = "crew_name") val crew_name : String,
//    @SerializedName(value = "crewId") val crew_id : Int,
//    @SerializedName(value = "recruitment_id") val recruitment_id : Int,
//    @SerializedName(value = "deadline") val deadline : Timestamp,
//    @SerializedName(value = "register_time") val register_time : Timestamp,
//    @SerializedName(value = "question_id") val question_id : Int
//    )

// 모집글 상세보기
data class RecruitmentDetailResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : DetailResult
)

data class DetailResult(
    @SerializedName(value = "content") val content : String,
    @SerializedName(value = "crew_Id") val crew_id : Int,
    @SerializedName(value = "question_id") val question_id : Int,
    @SerializedName(value = "register_time") val register_time : Timestamp,
    @SerializedName(value = "crew_profile") val crew_profile : String,
    @SerializedName(value = "crew_name") val crew_name : String,
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "field2") val field2 : String,
    @SerializedName(value = "field1") val field1 : String,
    @SerializedName(value = "region2") val region2 : String,
    @SerializedName(value = "region1") val region1 : String,
    @SerializedName(value = "deadline") val deadLine : Timestamp,
    @SerializedName(value = "image") val image : String
)