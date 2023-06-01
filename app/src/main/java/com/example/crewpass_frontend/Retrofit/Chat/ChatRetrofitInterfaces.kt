package com.example.crewpass_frontend.Retrofit.Chat

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ChatRetrofitInterfaces {
    @GET("/chat/history/{chatroomId}")
    fun getChatAll(@Path("chatroomId") chatroomId : Int): Call<ChatResponse>

}