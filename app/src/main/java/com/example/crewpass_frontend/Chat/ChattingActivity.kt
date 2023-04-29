package com.example.crewpass_frontend.Chat

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Chat
import com.example.crewpass_frontend.databinding.ActivityChattingBinding
import java.sql.Timestamp
import java.util.*

class ChattingActivity:AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    lateinit var chatRVAdapter: ChatRVAdapter
    var timestamp = Timestamp(Date().time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnDrawer.setOnClickListener {
            binding.drawerUserList.visibility = View.VISIBLE
            if(!binding.drawerUserList.isDrawerOpen(Gravity.RIGHT)){
                binding.drawerUserList.openDrawer(Gravity.RIGHT)
            }
        }

        // dummy data
        var chatDatas = mutableListOf<Chat>()
        chatRVAdapter = ChatRVAdapter(this)
        binding.recyclerMessages.adapter = chatRVAdapter
        binding.recyclerMessages.layoutManager = LinearLayoutManager(this).apply{
            this.stackFromEnd = true // 가장 최근의 대화를 표시하기 위해 맨 아래로 정렬.
        }

        chatRVAdapter.chatList = chatDatas
        chatRVAdapter.notifyDataSetChanged()

        initRecycler()
//        // 이전에 했던 채팅들 불러오기
//        val chatAllGetService = ChatAllGetService()
//        chatAllGetService.setChatAllGetResult(this)
//        chatAllGetService.getChatAll(chatRoom_id_get)
    }


    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "채팅"

    }

    fun initRecycler(){
        chatRVAdapter.addItem(
            Chat(
                0,
                0,
                0,
                timestamp,
                "안녕하세요~",
                "익명1",
                3
            )
        )

        chatRVAdapter.addItem(
            Chat(
                0,
                0,
                0,
                timestamp,
                "안녕하세요~",
                "익명1",
                2
            )
        )

        for(i in 0 until 10){
            if(i == 3){
                chatRVAdapter.addItem(
                    Chat(
                        0,
                        0,
                        0,
                        timestamp,
                        "안녕하세요~",
                        "성신한 집사들",
                        1
                    )
                )
            }else{
                chatRVAdapter.addItem(
                    Chat(
                        0,
                        0,
                        0,
                        timestamp,
                        "hi~"+ i.toString(),
                        "익명1",
                        1
                    )
                )
            }
        }
    }
}