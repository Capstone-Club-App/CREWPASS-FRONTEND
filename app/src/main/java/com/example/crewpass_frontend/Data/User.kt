package com.example.crewpass_frontend.Data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class User(
    @SerializedName(value ="user_id") val user_id : Int,
    @SerializedName(value ="user_login_id") val user_login_id : String,
    @SerializedName(value ="user_pw") val user_pw : String,
    @SerializedName(value ="user_name") val user_name : String,
    @SerializedName(value ="user_email") val user_email : String,
    @SerializedName(value ="job") val job : String,
    @SerializedName(value ="school") val school : String,
    @SerializedName(value ="user_profile") val user_profile : MultipartBody.Part
)
