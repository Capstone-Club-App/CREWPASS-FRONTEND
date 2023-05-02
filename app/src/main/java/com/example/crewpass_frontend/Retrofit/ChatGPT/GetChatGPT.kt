package com.example.crewpass_frontend.Retrofit.ChatGPT

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetChatGPT {

    private lateinit var chatGPTResult: ChatGPTResult

    fun setChatGetResult(chatGPTResult: ChatGPTResult){
        this.chatGPTResult = chatGPTResult
    }

    fun getChatGPT(){
        val contentType = "application/json"
        val apiKey = "YOUR_API_KEY_HERE"
        val prompt = "Hello, how are you doing today?"
        val maxTokens = 100

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/engines/davinci-codex/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ChatGPTService::class.java)

        val request = ChatGPTRequest(prompt, maxTokens)

        val call = service.getChatGPTResponse(contentType, "Bearer $apiKey", request)

        call.enqueue(object : Callback<ChatGPTResponse> {
            override fun onResponse(
                call: Call<ChatGPTResponse>,
                response: Response<ChatGPTResponse>
            ) {
                if (response.isSuccessful) {
                    val chatGPTResponse = response.body()
                    val chatGPTChoice = chatGPTResponse?.choices?.get(0)
                    val chatGPTText = chatGPTChoice?.text
                    // Chat GPT API의 응답을 처리하는 코드 작성
                    chatGPTResult.chatGPTSuccess(chatGPTText!!)
                } else {
                    // Chat GPT API에 실패한 경우 처리하는 코드 작성
                    chatGPTResult.chatGPTFailure()
                }
            }

            override fun onFailure(call: Call<ChatGPTResponse>, t: Throwable) {
                // Chat GPT API에 실패한 경우 처리하는 코드 작성
                chatGPTResult.chatGPTFailure()
            }
        })
    }
}