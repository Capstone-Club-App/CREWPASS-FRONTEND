package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityClubSignupEmailBinding

class ClubSignUpEmailActivity : AppCompatActivity() {
    lateinit var binding: ActivityClubSignupEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ClubSignUpDoneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }
}