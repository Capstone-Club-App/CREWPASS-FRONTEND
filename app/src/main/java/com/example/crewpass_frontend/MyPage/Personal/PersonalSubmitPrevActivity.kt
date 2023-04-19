package com.example.crewpass_frontend.MyPage.Personal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalSubmitPrevBinding

class PersonalSubmitPrevActivity:AppCompatActivity() {
    lateinit var binding: ActivityPersonalSubmitPrevBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSubmitPrevBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}