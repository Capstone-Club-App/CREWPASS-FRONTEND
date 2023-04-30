package com.example.crewpass_frontend.Home.Club.List

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Home.Club.List.Check.CheckApplicationActivity
import com.example.crewpass_frontend.databinding.ActivityClubRecruitmentDetailBinding

class ClubRecruitmentDetailActivity: AppCompatActivity() {
    lateinit var binding : ActivityClubRecruitmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubRecruitmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnCheckApplication.setOnClickListener {
            val intent = Intent(this, CheckApplicationActivity::class.java)
            startActivity(intent) // 지원서 작성으로 이동
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}