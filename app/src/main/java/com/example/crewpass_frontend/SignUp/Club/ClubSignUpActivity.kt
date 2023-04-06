package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityClubSignupBinding

class ClubSignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityClubSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ClubSignUpRegionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }
}