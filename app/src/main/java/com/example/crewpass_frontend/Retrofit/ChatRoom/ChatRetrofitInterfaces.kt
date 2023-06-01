package com.example.crewpass_frontend.Retrofit.ChatRoom

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ChatRoomRetrofitInterfaces {
    @GET("/chat/crew/myList")
    fun getChatRoomList_Crew (@Header("crewId") crewId : Int): Call<ChatRoomResponse>

    @GET("/chat/user/myList")
    fun getChatRoomList_User (@Header("userId") userId : Int): Call<ChatRoomResponse>

    @GET("/chat/info/{chatroomId}")
    fun getChatRoomInfo(@Path("chatroomId") chatRoomId : Int): Call<ChatRoomInfoResponse>
}