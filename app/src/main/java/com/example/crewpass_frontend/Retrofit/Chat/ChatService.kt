package com.example.crewpass_frontend.Retrofit.Chat

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatService {
    private lateinit var chatResult: ChatResult

    fun setChatResult(chatResult: ChatResult){
        this.chatResult = chatResult
    }

    fun getChatAll(chatroomId : Int){
        val chatService = getRetrofit().create(ChatRetrofitInterfaces::class.java)

        chatService.getChatAll(chatroomId).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>,) {
                Log.d("CHAT-GET SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            chatResult.getChatRoomListSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            chatResult.getChatRoomListFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHAT-GET FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.d("CHAT-GET FAILURE",t.message.toString())
            }
        })
    }
}