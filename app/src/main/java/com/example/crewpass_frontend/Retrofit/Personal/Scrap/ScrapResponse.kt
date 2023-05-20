package com.example.crewpass_frontend.Retrofit.Personal.Scrap

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

// 스크랩 등록
data class ScrapResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val scrap_id : Int
)

// 스크랩 취소
data class ScrapDeleteResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)

// 스크랩 목록 전체 불러오기
data class ScrapGetAllResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<getResult>
)

data class getResult(
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "field1") val field1 : String,
    @SerializedName(value = "field2") val field2 : String,
    @SerializedName(value = "region1") val region1 : String,
    @SerializedName(value = "region2") val region2 : String,
    @SerializedName(value = "deadLine") val deadLine : Timestamp,
    @SerializedName(value = "crew_name") val crew_name : String,
    @SerializedName(value = "crew_profile") val crew_profile : String,
    @SerializedName(value = "scrap_id") val scrap_id : Int,
    @SerializedName(value = "question_id") val question_id : Int,
    @SerializedName(value = "register_time") val register_time : Timestamp,
    @SerializedName(value = "crew_Id") val crew_id : Int,
    @SerializedName(value = "recruitment_id") val recruitment_id : Int,
)


