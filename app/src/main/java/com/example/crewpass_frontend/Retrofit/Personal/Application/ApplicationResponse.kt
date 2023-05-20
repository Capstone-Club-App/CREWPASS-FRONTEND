package com.example.crewpass_frontend.Retrofit.Personal.Application

import com.google.gson.annotations.SerializedName

data class ApplicationPostResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
    )

data class ApplicationData(
    @SerializedName("answer1") val answer1 : String,
    @SerializedName("answer2") val answer2 : String,
    @SerializedName("answer3") val answer3 : String,
    @SerializedName("answer4") val answer4 : String?,
    @SerializedName("answer5") val answer5 : String?,
    @SerializedName("answer6") val answer6 : String?,
    @SerializedName("answer7") val answer7 : String?,
    @SerializedName("answer1Count") val answer1Count : Int,
    @SerializedName("answer2Count") val answer2Count : Int,
    @SerializedName("answer3Count") val answer3Count : Int,
    @SerializedName("answer4Count") val answer4Count : Int?,
    @SerializedName("answer5Count") val answer5Count : Int?,
    @SerializedName("answer6Count") val answer6Count : Int?,
    @SerializedName("answer7Count") val answer7Count : Int?,
)