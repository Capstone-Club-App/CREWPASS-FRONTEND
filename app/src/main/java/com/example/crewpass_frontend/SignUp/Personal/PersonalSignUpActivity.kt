package com.example.crewpass_frontend.SignUp.Personal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityPersonalSignupBinding

class PersonalSignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener {
            val intent = Intent(this, PersonalSignUpChooseActivity::class.java)
             startActivity(intent)
            overridePendingTransition(0,0)
        }
    }
}