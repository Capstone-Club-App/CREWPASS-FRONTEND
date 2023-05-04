package com.example.crewpass_frontend.Data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Crew(
    @SerializedName(value ="crew_id") val crew_id : Int,
    @SerializedName(value ="crew_login_id") val crew_login_id : String,
    @SerializedName(value ="crew_pw") val crew_pw : String,
    @SerializedName(value ="crew_name") val crew_name : String,
    @SerializedName(value ="region1") val region1 : String,
    @SerializedName(value ="region2") val region2 : String?,
    @SerializedName(value ="field1") val field1 : String,
    @SerializedName(value ="field2") val field2 : String?,
    @SerializedName(value ="crew_master_email") val crew_master_email : String,
    @SerializedName(value ="crew_sub_email") val crew_sub_email : String,
    @SerializedName(value ="crew_profile") val crew_profile : MultipartBody.Part
)
