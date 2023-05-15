package com.example.crewpass_frontend.Retrofit.Club.Question

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class QuestionPostResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)