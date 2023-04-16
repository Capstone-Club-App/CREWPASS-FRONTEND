package com.example.crewpass_frontend.Chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityChattingBinding

class ChattingActivity:AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }


    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "채팅"

    }
}