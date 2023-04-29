package com.example.crewpass_frontend.Home.Club.List

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityCheckApplicationBinding

class CheckRecruitmentActivity:AppCompatActivity() {
    lateinit var binding: ActivityCheckApplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}