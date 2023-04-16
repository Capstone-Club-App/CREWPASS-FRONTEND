package com.example.crewpass_frontend.AI.Personal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalPrepareInterviewBinding

class PersonalPrepareInterviewActivity : AppCompatActivity(){
    lateinit var binding: ActivityPersonalPrepareInterviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalPrepareInterviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}