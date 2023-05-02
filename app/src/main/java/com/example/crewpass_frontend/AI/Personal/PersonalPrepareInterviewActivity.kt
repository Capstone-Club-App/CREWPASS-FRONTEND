package com.example.crewpass_frontend.AI.Personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalPrepareInterviewBinding

class PersonalPrepareInterviewActivity : AppCompatActivity(){
    lateinit var binding: ActivityPersonalPrepareInterviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalPrepareInterviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnPdf.setOnClickListener {

        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "면접 준비"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}