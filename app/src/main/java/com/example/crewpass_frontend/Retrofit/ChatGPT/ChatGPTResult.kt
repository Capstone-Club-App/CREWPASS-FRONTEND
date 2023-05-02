package com.example.crewpass_frontend.Retrofit.ChatGPT

interface ChatGPTResult {
    fun chatGPTSuccess(chatGPTText : String)
    fun chatGPTFailure()
}