package com.example.crewpass_frontend.AI.Personal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalChooseAnnouncementBinding

class PersonalChooseRecruitmentActivity:AppCompatActivity() {
    lateinit var binding: ActivityPersonalChooseAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalChooseAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, PersonalPrepareInterviewActivity::class.java)
            startActivity(intent)
        }
    }
}