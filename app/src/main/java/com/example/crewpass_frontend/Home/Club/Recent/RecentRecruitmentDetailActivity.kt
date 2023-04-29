package com.example.crewpass_frontend.Home.Club.Recent

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityRecentRecruitmentDetailBinding

class RecentRecruitmentDetailActivity: AppCompatActivity() {
    lateinit var binding : ActivityRecentRecruitmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentRecruitmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}