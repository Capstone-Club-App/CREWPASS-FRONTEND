package com.example.crewpass_frontend.Retrofit.Personal.Personal

import com.google.gson.annotations.SerializedName


// 회원 정보 조회
data class PersonalResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : PersonalData
)

data class PersonalData(
    @SerializedName(value = "userId") val userId : Int,
    @SerializedName(value = "userLoginId") val userLoginId : String,
    @SerializedName(value = "userPw") val userPw : String,
    @SerializedName(value = "userName") val userName : String,
    @SerializedName(value = "userEmail") val userEmail : String, // ai 기능 지원서 목록 바로 뽑을 수 있게!!
    @SerializedName(value = "job") val job : String,
    @SerializedName(value = "school") val school : String,
    @SerializedName(value = "userProfile") val userProfile : String,

    )

// 동아리 정보 수정
data class PersonalPutResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)