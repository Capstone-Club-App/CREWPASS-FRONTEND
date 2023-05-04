package com.example.crewpass_frontend.Data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class SignUp_Club(
    @SerializedName(value ="name") val crew_name : String,
    @SerializedName(value ="loginId") val crew_login_id : String,
    @SerializedName(value ="password") val crew_pw : String,
    @SerializedName(value ="region1") val region1 : String,
    @SerializedName(value ="region2") val region2 : String?,
    @SerializedName(value ="field1") val field1 : String,
    @SerializedName(value ="field2") val field2 : String?,
    @SerializedName(value ="masterEmail") val crew_master_email : String,
    @SerializedName(value ="subEmail") val crew_sub_email : String,
    @SerializedName(value ="profile") val crew_profile : MultipartBody.Part
)
