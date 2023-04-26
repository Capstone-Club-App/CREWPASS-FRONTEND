package com.example.crewpass_frontend.Retrofit.FindSchool

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName


data class SchoolGetResponse (
    @SerializedName("dataSearch") val dataSearch : Content
)

data class Content(
    @SerializedName("content") val dataList : MutableList<Data>
)

data class Data(
    @SerializedName(value = "totalCount") val totalCount : String,
    @SerializedName(value = "schoolName") val schoolName : String,
    @SerializedName(value = "schoolGubun") val schoolGubun : String,
    @SerializedName(value = "schoolType") val schoolType : String,
    @SerializedName(value = "estType") val estType : String,
    @SerializedName(value = "region") val region : String,
    @SerializedName(value = "adres") val adres : String,
    @SerializedName(value = "collegeinfourl") val collegeinfourl : String,
    @SerializedName(value = "link") val link : String
)
