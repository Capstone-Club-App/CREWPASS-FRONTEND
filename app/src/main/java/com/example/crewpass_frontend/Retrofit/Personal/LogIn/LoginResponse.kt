package com.example.crewpass_frontend.Retrofit.Personal.LogIn

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Data
)

data class Data(
    @SerializedName(value = "loginId") val loginId : String,
    @SerializedName(value = "password") val password : String
)