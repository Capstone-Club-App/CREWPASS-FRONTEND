package com.example.crewpass_frontend.Retrofit.ChatRoom

interface ChatRoomResult {
    fun getChatRoomListSuccess(code : Int, data : ArrayList<ChatRoomData>)
    fun getChatRoomListFailure(code : Int)
}

interface ChatRoomInfoResult {
    fun getChatRoominfoSuccess(code : Int, data : ChatRoomInfo)
    fun getChatRoominfoFailure(code : Int)
}