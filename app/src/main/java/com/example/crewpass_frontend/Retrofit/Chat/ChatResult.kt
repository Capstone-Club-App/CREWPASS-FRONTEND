package com.example.crewpass_frontend.Retrofit.Chat

interface ChatResult {
    fun getChatRoomListSuccess(code : Int, data : ArrayList<ChatData>)
    fun getChatRoomListFailure(code : Int)
}