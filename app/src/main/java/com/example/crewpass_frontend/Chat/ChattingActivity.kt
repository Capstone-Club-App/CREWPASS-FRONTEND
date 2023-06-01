package com.example.crewpass_frontend.Chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Chat
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Chat.ChatData
import com.example.crewpass_frontend.Retrofit.Chat.ChatResult
import com.example.crewpass_frontend.Retrofit.Chat.ChatService
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomInfo
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomInfoResult
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomService
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubData
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubGetResult
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubService
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalData
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalService
import com.example.crewpass_frontend.databinding.ActivityChattingBinding
import com.example.crewpass_frontend.databinding.LayoutChattingSlideBinding
import com.gmail.bishoybasily.stomp.lib.StompClient
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChattingActivity:AppCompatActivity(), PersonalGetResult, ClubGetResult, ChatRoomInfoResult ,ChatResult {
    lateinit var binding: ActivityChattingBinding
    lateinit var binding_drawer:LayoutChattingSlideBinding
    lateinit var chatRVAdapter: ChatRVAdapter
    var timestamp = Timestamp(Date().time)
    var chatRoom_id_get = -1
    var crewId_get = -1
    val format = SimpleDateFormat("HH:mm")
    var logined_name = ""

    // stromp 연결
    private val url = "ws://34.64.142.47:8080/ws/chat"
    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        binding_drawer = LayoutChattingSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatRoom_id_get = intent.getIntExtra("chatRoom_id", -1)
        if(chatRoom_id_get != -1){
            try {
                runStomp(chatRoom_id_get, logined_id)
            } catch (e: Exception) {
                Log.d("ERROR", "stomp 자체의 오류")
                Log.d("ERROR", e.message.toString())
            }
        }

        // 현재 로그인 유저 정보 불러오기
        val status = intent.getStringExtra("key")!! // 동아리인지 회원인지 가져오기
        if(status.equals("Club")){
            val personalService = PersonalService()
            personalService.setPersonalGetResult(this)
            personalService.getPersonal(logined_id.toString())
        }
        else{
            val clubService = ClubService()
            clubService.setClubGetResult(this)
            clubService.getClub(logined_id)
        }

        // 채팅방 정보 가져오기
        val chatRoomService = ChatRoomService()
        chatRoomService.setChatRoomInfoResult(this)
        chatRoomService.getChatRoomInfo(chatRoom_id_get)

        // dummy data
        var chatDatas = mutableListOf<Chat>()
        chatRVAdapter = ChatRVAdapter(this)
        binding.recyclerMessages.adapter = chatRVAdapter
        binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply{
            this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
        }

        chatRVAdapter.chatList = chatDatas
        chatRVAdapter.notifyDataSetChanged()

        // 이전 채팅 내역 불러오기
        val chatService = ChatService()
        chatService.setChatResult(this)
        chatService.getChatAll(chatRoom_id_get)
    }

    // 채팅 실행
    @SuppressLint("CheckResult")
    private fun runStomp(chatRoom_id: Int, user_id: Int) { // user_id는 현재 로그인한 유저 아이디!!!
        stompClient.connect()

        stompClient.topic("/sub/chatroom/${chatRoom_id}").subscribe { topicMessage ->
            Log.d("message Receive", topicMessage.payload)
            val senderName =
                JSONObject(topicMessage.payload).getString("senderName").toString()
            val content =
                JSONObject(topicMessage.payload).getString("content").toString()
            val crewId =
                JSONObject(topicMessage.payload).getString("crewId").toInt()
            val userId =
                JSONObject(topicMessage.payload).getString("userId").toInt() // 메세지 보낸 user_id 가져오기

            val time = JSONObject(topicMessage.payload).getString("sendTime").toString()
            val date = format.parse(time)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val extractedHour = calendar.get(Calendar.HOUR_OF_DAY) + 9
            val extractedMinute = calendar.get(Calendar.MINUTE)
            var sendTime = ""
            if(extractedHour > 12){
                sendTime = "오후 ${(extractedHour - 12)}:${extractedMinute}"
            }
            else
                sendTime = "오전 ${(extractedHour)}:${extractedMinute}"

            if (userId != user_id) { // 보낸 사람이 현재 로그인한 유저와 다른 사람
                chatRVAdapter.addItem(Chat(senderName, sendTime, content, userId, crewId, chatRoom_id_get, 1))
            }
            else
                chatRVAdapter.addItem(Chat(senderName, sendTime, content, userId, crewId, chatRoom_id_get, 2))

            binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply{
                this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
            }
        }

        stompClient.lifecycle().subscribe { lifecycleEvent ->
            when (lifecycleEvent.type) {
                LifecycleEvent.Type.OPENED -> {
                    Log.d("OPENED", "opened")
                }
                LifecycleEvent.Type.CLOSED -> {
                    Log.d("CLOSED", "closed")
                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("ERROR", "error")
                    Log.i("CONNECT ERROR", lifecycleEvent.exception.toString())
                }
                else -> {
                    Log.d("else", lifecycleEvent.message)
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            // 여기 수정하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            sendStomp(logined_name, binding.editMessage.text.toString(), chatRoom_id_get, crewId_get, logined_id)
            binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply{
                this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
            }
        }
    }

    // 채팅 보내기
    fun sendStomp(senderName: String, content: String, chatRoomId: Int,
    crewId : Int, userId : Int) {
        val data = JSONObject()
        data.put("senderName", senderName)
        data.put("content", content)
        data.put("chatRoomId", chatRoomId)
        data.put("crewId", crewId)
        data.put("userId", userId)

        stompClient.send("/pub/chat/message", data.toString()).subscribe()
        Log.d("보낸 메세지 : ", content)

        val time= System.currentTimeMillis()
        val time_to_timestamp = Timestamp(time)
        val sendTime = format.format(time_to_timestamp)

        chatRVAdapter.addItem(Chat(senderName, sendTime, content, logined_id, crewId, chatRoomId, 2))

    }

    // 로그인 계정 정보 가져오기
    override fun clubGetSuccess(code: Int, data: ClubData) {
        logined_name = data.crewName
    }

    override fun clubGetFailure(code: Int) {
        Log.d("동아리 정보 가져오기 실패", "")
    }

    override fun personalGetSuccess(code: Int, data: PersonalData) {
        logined_name = data.userName
    }

    override fun personalGetFailure(code: Int) {
        Log.d("유저 정보 가져오기 실패", "")
    }

    // 채팅방 정보 가져오기
    override fun getChatRoominfoSuccess(code: Int, data: ChatRoomInfo) {
        binding.itemClubNameTxt.text = data.crew_name
        binding.itemMembersNumTxt.text = data.count.toString()
    }

    override fun getChatRoominfoFailure(code: Int) {
        Log.d("채팅방 정보 가져오기 실패", "")
    }

    // 채팅 내역 불러오기
    override fun getChatRoomListSuccess(code: Int, data: ArrayList<ChatData>) {
        for(i in 0 until data.count()){
            val time= data[i].sendTime
            val sdf = SimpleDateFormat("HH:mm")
            val sendTime = sdf.format(time)
            if(data[i].senderName.equals(logined_name)){ // 로그인한 유저의 계정일 때
                chatRVAdapter.addItem(
                    Chat(
                        data[i].senderName,
                        sendTime,
                        data[i].content,
                        data[i].userId,
                        data[i].crewId,
                        data[i].chatRoomId,
                        2
                    )
                )
            }
            else{
                chatRVAdapter.addItem(
                    Chat(
                        data[i].senderName,
                        sendTime,
                        data[i].content,
                        data[i].userId,
                        data[i].crewId,
                        data[i].chatRoomId,
                        1
                    )
                )
            }
        }
    }

    override fun getChatRoomListFailure(code: Int) {
        Log.d("채팅 내역 불러오기 실패", "")
    }
}