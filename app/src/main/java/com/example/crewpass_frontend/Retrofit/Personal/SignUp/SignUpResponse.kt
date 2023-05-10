package com.example.crewpass_frontend.Retrofit.Personal.SignUp

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : String?
)