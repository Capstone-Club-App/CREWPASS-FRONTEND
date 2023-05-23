package com.example.crewpass_frontend.Retrofit.Personal.FindPWID

import com.google.gson.annotations.SerializedName

data class FindIdEmailResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ResultEmail
)
data class ResultEmail(
    @SerializedName(value ="certificateNumb") val certificateNumb : Int
)

data class FindIdNumbResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ResultNumb
)
data class ResultNumb(
    @SerializedName(value ="crew_user_id") val crew_user_id : Int?,
    @SerializedName(value ="loginId") val loginId : String,
    @SerializedName(value ="password") val password : String?
)


