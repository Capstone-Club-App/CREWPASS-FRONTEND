package com.example.crewpass_frontend.Retrofit.Club.Club

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.sql.Timestamp


// 동아리 정보 조회
data class ClubResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ClubData
)

data class ClubData(
    @SerializedName(value = "crewId") val crew_id : Int,
    @SerializedName(value = "crewLoginId") val crewLoginId : String,
    @SerializedName(value = "crewPw") val crewPw : String,
    @SerializedName(value = "crewName") val crewName : String,
    @SerializedName(value = "region1") val region1 : String,
    @SerializedName(value = "region2") val region2 : String,
    @SerializedName(value = "field1") val field1 : String,
    @SerializedName(value = "field2") val field2 : String,
    @SerializedName(value = "crewMasterEmail") val crewMasterEmail : String,
    @SerializedName(value = "crewSubEmail") val crewSubEmail : String, // ai 기능 지원서 목록 바로 뽑을 수 있게!!
    @SerializedName(value = "crewProfile") val crew_profile : String,

    )

// 동아리 정보 수정
data class ClubPutResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)