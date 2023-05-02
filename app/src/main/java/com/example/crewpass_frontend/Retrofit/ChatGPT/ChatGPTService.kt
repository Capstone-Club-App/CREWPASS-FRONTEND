package com.example.crewpass_frontend.Retrofit.ChatGPT

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatGPTService {
    @POST("completions")
    fun getChatGPTResponse(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") apiKey: String,
        @Body request: ChatGPTRequest
    ): Call<ChatGPTResponse>
}