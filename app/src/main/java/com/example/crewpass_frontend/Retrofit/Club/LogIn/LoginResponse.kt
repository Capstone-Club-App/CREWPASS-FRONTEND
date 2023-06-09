package com.example.crewpass_frontend.Retrofit.Club.LogIn

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ClubData
)

data class ClubData(
    @SerializedName(value = "crew_user_id") val crew_user_id : Int,
    @SerializedName(value = "loginId") val loginId : String,
    @SerializedName(value = "password") val password : String
)