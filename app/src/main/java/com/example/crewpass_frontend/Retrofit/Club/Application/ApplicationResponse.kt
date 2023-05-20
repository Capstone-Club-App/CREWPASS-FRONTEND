package com.example.crewpass_frontend.Retrofit.Club.Application
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class ApplicationGetResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<ApplicationData>
    )

data class ApplicationData(
    @SerializedName("submit_time") val submit_time : Timestamp,
    @SerializedName("application_id") val application_id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("user_profile") val user_profile : String?,
    @SerializedName("user_name") val user_name : String
)

// 합불통보
data class PassNpassResponse(
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)