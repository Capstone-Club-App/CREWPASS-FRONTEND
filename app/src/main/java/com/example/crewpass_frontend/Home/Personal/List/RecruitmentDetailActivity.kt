package com.example.crewpass_frontend.Home.Personal.List

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityAnnouncementDetailBinding

class RecruitmentDetailActivity: AppCompatActivity() {
    lateinit var binding : ActivityAnnouncementDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        val key = intent.getBooleanExtra("scrap", false)
        Log.d("Key : ", key.toString())

        if (key)
            binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, SubmitApplicationActivity::class.java)
            startActivity(intent) // 지원서 작성으로 이동
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}