package com.example.crewpass_frontend.AI.Club.Question

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Login.LoginActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.databinding.ActivityAnalyzeApplicationBinding

class AnalyzeApplicationActivity: AppCompatActivity() {
    lateinit var binding : ActivityAnalyzeApplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyzeApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnDone.setOnClickListener {

        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 질문 AI 추천"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}