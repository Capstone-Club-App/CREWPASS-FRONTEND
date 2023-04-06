package com.example.crewpass_frontend.IDPW_Find

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityIdPwFindBinding

class IDPWFindActivity :AppCompatActivity(){
    var current_state = ""
    lateinit var binding: ActivityIdPwFindBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdPwFindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.IDBtn.setOnClickListener {
            current_state = "ID"
        }

        binding.PWBtn.setOnClickListener {
            current_state = "PW"
        }

        binding.btnLogin.setOnClickListener {
            finish()
        }
    }
}