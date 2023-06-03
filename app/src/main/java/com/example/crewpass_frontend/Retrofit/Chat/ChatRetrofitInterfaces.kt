package com.example.crewpass_frontend.Retrofit.Chat

import retrofit2.Call
import retrofit2.http.*

interface ChatRetrofitInterfaces {
    @GET("/chat/history/{chatroomId}")
    fun getChatAll(@Path("chatroomId") chatroomId : Int): Call<ChatResponse>

    @PUT("/chat/history/{chatRoomId}/crew")
    fun putLastChatClub(@Header("crewId") crewId : Int, @Path("chatRoomId") chatroomId : Int): Call<ChatPutResponse>

    @PUT("/chat/history/{chatRoomId}/user")
    fun putLastChatPersonal(@Header("userId") crewId : Int, @Path("chatRoomId") chatroomId : Int): Call<ChatPutResponse>
}