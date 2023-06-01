package com.example.crewpass_frontend.Retrofit.ChatRoom

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRoomService {
    private lateinit var chatRoomResult: ChatRoomResult

    fun setChatRoomResult(chatRoomResult: ChatRoomResult){
        this.chatRoomResult = chatRoomResult
    }

    fun getChatRoom_Club(crewId : Int){
        val chatService = getRetrofit().create(ChatRoomRetrofitInterfaces::class.java)

        chatService.getChatRoomList_Crew(crewId).enqueue(object : Callback<ChatRoomResponse> {
            override fun onResponse(call: Call<ChatRoomResponse>, response: Response<ChatRoomResponse>,) {
                Log.d("CHATROOM-GET SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatRoomResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            chatRoomResult.getChatRoomListSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            chatRoomResult.getChatRoomListFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHATROOM-GET FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatRoomResponse>, t: Throwable) {
                Log.d("CHATROOM-GET FAILURE",t.message.toString())
            }
        })
    }

    fun getChatRoom_User(userId : Int){
        val chatService = getRetrofit().create(ChatRoomRetrofitInterfaces::class.java)

        chatService.getChatRoomList_User(userId).enqueue(object : Callback<ChatRoomResponse> {
            override fun onResponse(call: Call<ChatRoomResponse>, response: Response<ChatRoomResponse>,) {
                Log.d("CHATROOM-GET SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatRoomResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            chatRoomResult.getChatRoomListSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            chatRoomResult.getChatRoomListFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHATROOM-GET FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatRoomResponse>, t: Throwable) {
                Log.d("CHATROOM-GET FAILURE",t.message.toString())
            }
        })
    }

    // 채팅방 정보 가져오기
    private lateinit var chatRoomInfoResult: ChatRoomInfoResult

    fun setChatRoomInfoResult(chatRoomInfoResult: ChatRoomInfoResult){
        this.chatRoomInfoResult = chatRoomInfoResult
    }

    fun getChatRoomInfo(chatRoomId : Int){
        val chatService = getRetrofit().create(ChatRoomRetrofitInterfaces::class.java)

        chatService.getChatRoomInfo(chatRoomId).enqueue(object : Callback<ChatRoomInfoResponse> {
            override fun onResponse(call: Call<ChatRoomInfoResponse>, response: Response<ChatRoomInfoResponse>,) {
                Log.d("CHAT-GET SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: ChatRoomInfoResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            chatRoomInfoResult.getChatRoominfoSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            chatRoomInfoResult.getChatRoominfoFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("CHATROOM-GET FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ChatRoomInfoResponse>, t: Throwable) {
                Log.d("CHATROOM-GET FAILURE",t.message.toString())
            }
        })
    }
}