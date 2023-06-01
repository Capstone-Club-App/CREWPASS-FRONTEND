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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        initActionBar()

        val key = arguments?.getString("Key")!! // 동아리인지 회원인지 가져오기
        Log.d("key : ", key)
        status = key

        val chatRoomService = ChatRoomService()
        chatRoomService.setChatRoomResult(this)
        if (key.equals("Club")){
            chatRoomService.getChatRoom_Club(logined_id)
        }
        else{
            chatRoomService.getChatRoom_User(logined_id)
        }

        return binding.root
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "채팅"

    }


    override fun getChatRoomListSuccess(code: Int, data: ArrayList<ChatRoomData>) {
        chatRoomRVAdapter = ChatRoomRVAdapter(data)
        binding.rvChatroom.adapter = chatRoomRVAdapter
        binding.rvChatroom.layoutManager = LinearLayoutManager(context)
        chatRoomRVAdapter.setItemClickListener(object :
            ChatRoomRVAdapter.OnItemClickListener {
            override fun onItemClick(chatRoom: ChatRoomData) {
                val intent = Intent(context, ChattingActivity::class.java)
//                intent.putExtra("crewId", chatRoom.c)
                intent.putExtra("status", status)
                startActivity(intent) // 지원서 작성으로 이동
            }
        })
    }

    override fun getChatRoomListFailure(code: Int) {
        Log.d("채팅방 리스트 가져오기 실패", "")
    }
}