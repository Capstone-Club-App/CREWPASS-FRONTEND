package com.example.crewpass_frontend.Retrofit.Chat

interface ChatResult {
    fun getChatAllSuccess(code : Int, data : ArrayList<ChatData>)
    fun getChatAllFailure(code : Int)
}

interface PutLastChatClubResult {
    fun putLastChatClubSuccess(code : Int)
    fun putLastChatClubFailure(code : Int)
}

interface PutLastChatPersonalResult {
    fun putLastChatPersonalSuccess(code : Int)
    fun putLastChatPersonalFailure(code : Int)
}