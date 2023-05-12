package com.example.crewpass_frontend.SignUp.Personal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Login.LoginActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalSignupDoneBinding

class PersonalSignUpDoneActivity : AppCompatActivity() {
    lateinit var binding : ActivityPersonalSignupDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSignupDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnDone.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
    }
}