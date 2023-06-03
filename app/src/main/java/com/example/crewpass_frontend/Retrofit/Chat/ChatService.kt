package com.example.crewpass_frontend.Retrofit.Chat

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatService {
    // 전체 채팅내역 불러오기
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
                            chatResult.getChatAllSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            chatResult.getChatAllFailure(resp.statusCode)
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

    // 동아리 lastChat 갱신
    private lateinit var putLastChatClubResult: PutLastChatClubResult

    fun setPutLastChatClubResult(putLastChatClubResult: PutLastChatClubResult){
        this.putLastChatClubResult = putLastChatClubResult
    }

    fun putLastChatClub(crewId : Int, chatroomId : Int){
        val chatService = getRetrofit().create(ChatRetrofitInterfaces::class.java)

        chatService.putLastChatClub(crewId, chatroomId).enqueue(object : Callback<ChatPutResponse> {
            override fun onResponse(call: Call<ChatPutResponse>, response: Response<ChatPutResponse>,) {
                Log.d("CHAT-PUT-CLUB SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatPutResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            putLastChatClubResult.putLastChatClubSuccess(resp.statusCode)
                        }
                        else -> {
                            putLastChatClubResult.putLastChatClubFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHAT-PUT-CLUB FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatPutResponse>, t: Throwable) {
                Log.d("CHAT-PUT-CLUB FAILURE",t.message.toString())
            }
        })
    }

    // 회원 lastChat 갱신
    private lateinit var putLastChatPersonalResult: PutLastChatPersonalResult

    fun setPutLastChatPersonalResult(putLastChatPersonalResult: PutLastChatPersonalResult){
        this.putLastChatPersonalResult = putLastChatPersonalResult
    }

    fun putLastChatPersonal(userId : Int, chatroomId : Int){
        val chatService = getRetrofit().create(ChatRetrofitInterfaces::class.java)

        chatService.putLastChatPersonal(userId, chatroomId).enqueue(object : Callback<ChatPutResponse> {
            override fun onResponse(call: Call<ChatPutResponse>, response: Response<ChatPutResponse>,) {
                Log.d("CHAT-PUT-P SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatPutResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            putLastChatPersonalResult.putLastChatPersonalSuccess(resp.statusCode)
                        }
                        else -> {
                            putLastChatPersonalResult.putLastChatPersonalFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHAT-PUT-P FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatPutResponse>, t: Throwable) {
                Log.d("CHAT-PUT-P FAILURE",t.message.toString())
            }
        })
    }
}