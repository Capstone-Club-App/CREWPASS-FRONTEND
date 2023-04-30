package com.example.crewpass_frontend.Chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Chat
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityChattingBinding
import com.example.crewpass_frontend.databinding.LayoutChattingSlideBinding
import java.sql.Timestamp
import java.util.*

class ChattingActivity:AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityChattingBinding
    lateinit var binding_drawer:LayoutChattingSlideBinding
    lateinit var chatRVAdapter: ChatRVAdapter
    var timestamp = Timestamp(Date().time)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        binding_drawer = LayoutChattingSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()


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
        binding.btnDrawer.setOnClickListener(this)
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

    override fun onClick(view: View?) {
        view?.let{
            when(it.id){
                R.id.btn_drawer -> {
                    if(binding.drawerLayout.isDrawerOpen(binding_drawer.llDrawer))
                        binding.drawerLayout.closeDrawer(binding_drawer.llDrawer)
                    else
                        binding.drawerLayout.openDrawer(binding_drawer.llDrawer)
                }
            }
        }
    }
}