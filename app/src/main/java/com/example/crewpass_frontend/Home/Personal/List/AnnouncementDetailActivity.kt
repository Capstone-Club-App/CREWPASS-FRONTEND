package com.example.crewpass_frontend.Home.Personal.List

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityAnnouncementDetailBinding

class AnnouncementDetailActivity: AppCompatActivity() {
    lateinit var binding : ActivityAnnouncementDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}