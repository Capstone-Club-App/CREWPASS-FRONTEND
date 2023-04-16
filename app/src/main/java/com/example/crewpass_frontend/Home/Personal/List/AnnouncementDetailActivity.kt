package com.example.crewpass_frontend.Home.Personal.List

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityAnnouncementDetailBinding

class AnnouncementDetailActivity: AppCompatActivity() {
    lateinit var binding : ActivityAnnouncementDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, SubmitAnnouncementActivity::class.java)
            startActivity(intent) // 지원서 작성으로 이동
        }
    }
}