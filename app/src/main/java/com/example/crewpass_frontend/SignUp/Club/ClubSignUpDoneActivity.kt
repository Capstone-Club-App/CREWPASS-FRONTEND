package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Login.LoginActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityClubSignupDoneBinding

class ClubSignUpDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityClubSignupDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDone.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
    }
}