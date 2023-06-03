package com.example.crewpass_frontend.Retrofit.ChatRoom

import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalData
import com.google.gson.annotations.SerializedName


data class ChatRoomResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<ChatRoomData>
)

data class ChatRoomData(
    @SerializedName(value = "title") val title : String,
    @SerializedName(value = "crew_name") val crew_name : String,
    @SerializedName(value = "crew_profile") val crew_profile : String,
    @SerializedName(value ="crew_id") val crew_id : Int,
    @SerializedName(value ="chat_room_id") val chat_room_id : Int
)

data class ChatRoomInfoResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ChatRoomInfo?
)

data class ChatRoomInfo(
    @SerializedName(value = "count") val count : Int?,
    @SerializedName(value = "crew_name") val crew_name : String?
)