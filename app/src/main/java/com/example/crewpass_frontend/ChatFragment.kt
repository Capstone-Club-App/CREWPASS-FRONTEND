package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Chat.ChatRoomRVAdapter
import com.example.crewpass_frontend.Chat.ChattingActivity
import com.example.crewpass_frontend.Data.Announcement
import com.example.crewpass_frontend.Data.ChatRoom
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementRVAdapter
import com.example.crewpass_frontend.databinding.FragmentChatBinding
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ChatFragment : Fragment() {
    lateinit var binding : FragmentChatBinding
    lateinit var chatRoomRVAdapter: ChatRoomRVAdapter
    var chatRomm_list = ArrayList<ChatRoom>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        initRecycler()
        initActionBar()



        return binding.root
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "채팅"

    }

    fun initRecycler(){
        chatRomm_list.apply {
            add(ChatRoom("동아리1", "제목1", "오후 12시 33분"))
            add(ChatRoom("동아리2", "제목2", "오전 11시 10분"))

            chatRoomRVAdapter = ChatRoomRVAdapter(chatRomm_list)
            binding.rvChatroom.adapter = chatRoomRVAdapter
            binding.rvChatroom.layoutManager = LinearLayoutManager(context)
            chatRoomRVAdapter.setItemClickListener(object :
                ChatRoomRVAdapter.OnItemClickListener {
                override fun onItemClick(chatRoom: ChatRoom) {
                    val intent = Intent(context, ChattingActivity::class.java)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }
}