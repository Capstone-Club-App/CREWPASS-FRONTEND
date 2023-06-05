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

interface GetNotReadChatClubResult {
    fun getNotReadChatClubSuccess(code : Int, data : NotReadChatData)
    fun getNotReadChatClubFailure(code : Int)
}

interface GetNotReadChatPersonalResult {
    fun getNotReadChatPersonalSuccess(code : Int, data : NotReadChatData)
    fun getNotReadChatPersonalFailure(code : Int)
}