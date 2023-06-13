package com.example.crewpass_frontend.Chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Chat
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Chat.*
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomInfo
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomInfoResult
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomService
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubData
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubGetResult
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubService
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalData
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalService
import com.example.crewpass_frontend.Timestamp_to_SDF
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

class ChattingActivity : AppCompatActivity(), PersonalGetResult, ClubGetResult, ChatRoomInfoResult,
    ChatResult, PutLastChatClubResult, PutLastChatPersonalResult {
    lateinit var binding: ActivityChattingBinding
    lateinit var binding_drawer: LayoutChattingSlideBinding
    lateinit var chatRVAdapter: ChatRVAdapter
    lateinit var edit_message: EditText
    var timestamp = Timestamp(Date().time)
    var chatRoom_id_get = -1
    var crewId_get = -1
    val format = SimpleDateFormat("HH:mm")
    var logined_name = ""
    var crew_name_get = ""
    var login_status = ""

    // stromp 연결
    private val url = "ws://34.64.142.47:8080/ws/chat"
    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        binding_drawer = LayoutChattingSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatRoom_id_get = intent.getIntExtra("chatRoom_id", -1)
        // 이전 채팅 내역 불러오기
        val chatService = ChatService()
        chatService.setChatResult(this)
        chatService.getChatAll(chatRoom_id_get)

        crewId_get = intent.getIntExtra("crewId", -1)
        crew_name_get = intent.getStringExtra("crew_name")!!
        if (chatRoom_id_get != -1) {
            try {
                runStomp(chatRoom_id_get, logined_id)
            } catch (e: Exception) {
                Log.d("ERROR", "stomp 자체의 오류")
                Log.d("ERROR", e.message.toString())
            }
        }

        edit_message = binding.editMessage
        edit_message!!.addTextChangedListener(textWatcher)

        // 현재 로그인 유저 정보 불러오기
        val status = intent.getStringExtra("status")!! // 동아리인지 회원인지 가져오기
        Log.d("status : ", status)
        login_status = status
        if (status.equals("Club")) {
            val clubService = ClubService()
            clubService.setClubGetResult(this)
            clubService.getClub(logined_id)
        } else {
            val personalService = PersonalService()
            personalService.setPersonalGetResult(this)
            personalService.getPersonal(logined_id.toString())
        }

        // lastChat 갱신
        if (login_status.equals("Club")) {
            val chatService = ChatService()
            chatService.setPutLastChatClubResult(this)
            chatService.putLastChatClub(logined_id, chatRoom_id_get)
        } else {
            val chatService = ChatService()
            chatService.setPutLastChatPersonalResult(this)
            chatService.putLastChatPersonal(logined_id, chatRoom_id_get)
        }

        // 채팅방 정보 가져오기
        val chatRoomService = ChatRoomService()
        chatRoomService.setChatRoomInfoResult(this)
        chatRoomService.getChatRoomInfo(chatRoom_id_get)

        // dummy data
        var chatDatas = mutableListOf<Chat>()
        chatRVAdapter = ChatRVAdapter(this, crew_name_get)
        binding.recyclerMessages.adapter = chatRVAdapter
        binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply {
            this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
        }

        chatRVAdapter.chatList = chatDatas
        chatRVAdapter.notifyDataSetChanged()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    // 채팅창 설정
    val textWatcher = object : TextWatcher {

        override
        fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override
        fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            if (binding.editMessage.text == null)
                binding.btnSubmit.isClickable = false
            else
                binding.btnSubmit.isClickable = true
        }
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
                if (!JSONObject(topicMessage.payload).isNull("crewId"))
                    JSONObject(topicMessage.payload).getString("crewId").toInt()
                else
                    null
            val userId =
                if (!JSONObject(topicMessage.payload).isNull("userId"))
                    JSONObject(topicMessage.payload).getString("userId").toInt()
                else
                    null
            val chatId =
                JSONObject(topicMessage.payload).getString("chatId").toInt()

            // sendTime 설정
            val dateString = JSONObject(topicMessage.payload).getString("sendTime")
            var sendTime = dateString.substring(11 until 16)
            val hour = sendTime.substring(0 until 2)
            val minute = sendTime.subSequence(3 until 5)
            if(hour.toInt() >= 12) {
                if(hour.toInt() != 12)
                    sendTime = "오후 ${hour.toInt() - 12}:${minute}"
                else
                    sendTime = "오후 ${hour.toInt()}:${minute}"
            }
            else
                sendTime = "오전 ${hour}:${minute}"


            if ((crewId == null && userId == logined_id)
                || (userId == null && crewId == logined_id)) { // 로그인한 유저의 계정일 때
//                chatRVAdapter.addItem(
//                    Chat(
//                        chatId,
//                        senderName,
//                        sendTime,
//                        content,
//                        userId,
//                        crewId,
//                        chatRoom_id_get,
//                        2
//                    )
//                )
            } else
                chatRVAdapter.addItem(
                    Chat(
                        chatId,
                        senderName,
                        sendTime,
                        content,
                        userId,
                        crewId,
                        chatRoom_id_get,
                        1
                    )
                )
            runOnUiThread {
                binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply {
                    this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
                }
                chatRVAdapter.notifyDataSetChanged()
            }

            // lastChat 갱신
            if (login_status.equals("Club")) {
                val chatService = ChatService()
                chatService.setPutLastChatClubResult(this)
                chatService.putLastChatClub(logined_id, chatRoom_id_get)
            } else {
                val chatService = ChatService()
                chatService.setPutLastChatPersonalResult(this)
                chatService.putLastChatPersonal(logined_id, chatRoom_id_get)
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
            if (login_status.equals("Club"))
                sendStomp(
                    logined_name,
                    binding.editMessage.text.toString(),
                    chatRoom_id_get,
                    logined_id,
                    null
                )
            else
                sendStomp(
                    logined_name,
                    binding.editMessage.text.toString(),
                    chatRoom_id_get,
                    null,
                    logined_id
                )
            binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply {
                this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
            }
        }
    }

    // 채팅 보내기
    fun sendStomp(
        senderName: String, content: String, chatRoomId: Int,
        crewId: Int?, userId: Int?
    ) {
        val data = JSONObject()
        data.put("senderName", senderName)
        data.put("content", content)
        data.put("chatRoomId", chatRoomId)
        if (crewId == null)
            data.put("crewId", JSONObject.NULL)
        else
            data.put("crewId", crewId)
        if (userId == null)
            data.put("userId", JSONObject.NULL)
        else
            data.put("userId", userId)

        stompClient.send("/pub/chat/message", data.toString()).subscribe()
        Log.d("보낸 메세지 : ", content)
        edit_message.text = Editable.Factory.getInstance().newEditable("") // 채팅 입력창 다시 초기화시켜주기

        val time = System.currentTimeMillis()
        val time_to_timestamp = Timestamp(time)
        var sendTime = format.format(time_to_timestamp)
        Log.d("sendTime : ", sendTime)
        val hour = sendTime.substring(0 until 2)
        val minute = sendTime.subSequence(3 until 5)
        if(hour.toInt() >= 12) {
            if(hour.toInt() != 12)
                sendTime = "오후 ${hour.toInt() - 12}:${minute}"
            else
                sendTime = "오후 ${hour.toInt()}:${minute}"
        }
        else
            sendTime = "오전 ${hour}:${minute}"

        // 내가 보낸 채팅이기 때문에 유저정보 필요하지 않으니 우선 chat_id -1로 두기
        chatRVAdapter.addItem(
            Chat(
                -1,
                senderName,
                sendTime,
                content,
                userId,
                crewId,
                chatRoomId,
                2
            )
        )

        runOnUiThread {
            binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply {
                this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
            }
            chatRVAdapter.notifyDataSetChanged()
        }

//        // lastChat 갱신
//        if (login_status.equals("Club")) {
//            val chatService = ChatService()
//            chatService.setPutLastChatClubResult(this)
//            chatService.putLastChatClub(logined_id, chatRoom_id_get)
//        } else {
//            val chatService = ChatService()
//            chatService.setPutLastChatPersonalResult(this)
//            chatService.putLastChatPersonal(logined_id, chatRoom_id_get)
//        }
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
    override fun getChatRoominfoSuccess(code: Int, data: ChatRoomInfo?) {
        binding.itemClubNameTxt.text = data?.crew_name
        binding.itemMembersNumTxt.text = data?.count.toString()
    }

    override fun getChatRoominfoFailure(code: Int) {
        Log.d("채팅방 정보 가져오기 실패", "")
    }

    // 채팅 내역 불러오기
    override fun getChatAllSuccess(code: Int, data: ArrayList<ChatData>) {
        for (i in 0 until data.count()) {
            val dateString = data[i].sendTime
            var sendTime = dateString.substring(11 until 16)
            val hour = sendTime.substring(0 until 2)
            val minute = sendTime.subSequence(3 until 5)
            if(hour.toInt() > 12) {
                sendTime = "오후 ${hour.toInt() - 12}:${minute}"
            }
            else
                sendTime = "오전 ${hour}:${minute}"

            if ((data[i].crewId == null && data[i].userId == logined_id)
                || (data[i].userId == null && data[i].crewId == logined_id)) { // 로그인한 유저의 계정일 때
                chatRVAdapter.addItem(
                    Chat(
                        data[i].chatId,
                        data[i].senderName,
                        sendTime,
                        data[i].content,
                        data[i].userId,
                        data[i].crewId,
                        data[i].chatRoomId,
                        2
                    )
                )
            } else {
                chatRVAdapter.addItem(
                    Chat(
                        data[i].chatId,
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
        chatRVAdapter.notifyDataSetChanged()
    }

    override fun getChatAllFailure(code: Int) {
        Log.d("채팅 내역 불러오기 실패", "")
    }

    // 최근에 읽은 채팅 수 갱신
    override fun putLastChatClubSuccess(code: Int) {
        Log.d("동아리 최근에 읽은 채팅 수 갱신 성공", "")
    }

    override fun putLastChatClubFailure(code: Int) {
        Log.d("동아리 최근에 읽은 채팅 수 갱신 실패", "")
    }

    override fun putLastChatPersonalSuccess(code: Int) {
        Log.d("회원 최근에 읽은 채팅 수 갱신 성공", "")
    }

    override fun putLastChatPersonalFailure(code: Int) {
        Log.d("회원 최근에 읽은 채팅 수 갱신 실패", "")
    }
}