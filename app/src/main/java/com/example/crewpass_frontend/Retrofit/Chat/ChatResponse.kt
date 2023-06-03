package com.example.crewpass_frontend.Retrofit.Chat

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp


data class ChatResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : ArrayList<ChatData>
)

data class ChatPutResponse (
    @SerializedName(value ="statusCode") val statusCode : Int,
    @SerializedName(value = "responseMessage") val responseMessage : Any?,
    @SerializedName(value = "data") val data : Any?
)

data class ChatData(
    @SerializedName(value = "chatId") val chatId : Int,
    @SerializedName(value = "senderName") val senderName : String,
    @SerializedName(value = "sendTime") val sendTime : String,
    @SerializedName(value = "content") val content : String,
    @SerializedName(value = "userId") val userId : Int?,
    @SerializedName(value = "crewId") val crewId : Int?,
    @SerializedName(value = "chatRoomId") val chatRoomId : Int
)
