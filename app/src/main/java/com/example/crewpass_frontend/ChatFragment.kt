package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Chat.ChatRoomRVAdapter
import com.example.crewpass_frontend.Chat.ChattingActivity
import com.example.crewpass_frontend.Data.ChatRoom
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomData
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomResult
import com.example.crewpass_frontend.Retrofit.ChatRoom.ChatRoomService
import com.example.crewpass_frontend.databinding.FragmentChatBinding

class ChatFragment : Fragment(), ChatRoomResult {
    lateinit var binding : FragmentChatBinding
    lateinit var chatRoomRVAdapter: ChatRoomRVAdapter
    var status = ""
    var key = ""
    val chatRoomService = ChatRoomService()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        initActionBar()

        key = arguments?.getString("Key")!! // 동아리인지 회원인지 가져오기
        Log.d("key : ", key)
        status = key
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        chatRoomService.setChatRoomResult(this)
        if (key.equals("Club")){
            chatRoomService.getChatRoom_Club(logined_id)
        }
        else{
            chatRoomService.getChatRoom_User(logined_id)
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "채팅"

    }


    override fun getChatRoomListSuccess(code: Int, data: ArrayList<ChatRoomData>) {
        chatRoomRVAdapter = ChatRoomRVAdapter(data, key)
        binding.rvChatroom.adapter = chatRoomRVAdapter
        binding.rvChatroom.layoutManager = LinearLayoutManager(context)
        chatRoomRVAdapter.setItemClickListener(object :
            ChatRoomRVAdapter.OnItemClickListener {
            override fun onItemClick(chatRoom: ChatRoomData) {
                val intent = Intent(context, ChattingActivity::class.java)
                intent.putExtra("chatRoom_id", chatRoom.chat_room_id)  // 여기 수정!!!
                intent.putExtra("crew_name", chatRoom.crew_name)
                intent.putExtra("crewId", chatRoom.crew_id)
                intent.putExtra("status", status)
                intent.putExtra("is_connected", !chatRoom.is_connected)
                startActivity(intent) // 지원서 작성으로 이동
            }
        })
    }

    override fun getChatRoomListFailure(code: Int) {
        Log.d("채팅방 리스트 가져오기 실패", "")
    }
}