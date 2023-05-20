package com.example.crewpass_frontend.Retrofit.Club.Recruitment

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.sql.Timestamp

// 모집글 리스트 가져오기
data class RecruitmentResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<RecruitmentData>
)

data class RecruitmentData(
    @SerializedName(value = "crew_id") val crew_id : Int,
    @SerializedName(value = "crew_profile") val crew_profile : MultipartBody.Part,
    @SerializedName(value = "crew_name") val crew_name : String,
    @SerializedName(value = "region1") val region1 : String,
    @SerializedName(value = "region2") val region2 : String,
    @SerializedName(value = "field1") val field1 : String,
    @SerializedName(value = "field2") val field2 : String,
    @SerializedName(value = "recruitment_id") val recruitment_id : Int, // 상세조회 param 값!!
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "register_time") val register_time : Timestamp,
    @SerializedName(value = "question_id") val question_id : Int, // ai 기능 지원서 목록 바로 뽑을 수 있게!!
)

// 모집글 등록하기
data class RecruitmentPostResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : PostResult
)

data class PostResult(
    @SerializedName(value = "recruitmentId") val recruitment_id : Int
)