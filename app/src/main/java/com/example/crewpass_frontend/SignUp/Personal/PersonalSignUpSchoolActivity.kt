package com.example.crewpass_frontend.SignUp.Personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.SignUp.Personal.FindSchool.FindSchoolDialog
import com.example.crewpass_frontend.databinding.ActivityPersonalSignupSchoolBinding

class PersonalSignUpSchoolActivity : AppCompatActivity(), FindSchoolDialog.CustomDialogInterface {
    lateinit var binding: ActivityPersonalSignupSchoolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSignupSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gubun = intent.getStringExtra("gubun")


        binding.btnFindSchool.setOnClickListener {
            val findSchoolDialog = FindSchoolDialog(this, this, gubun!!)
            findSchoolDialog.show()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, PersonalSignUpDoneActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }


    }

    override fun onOkButtonClicked() {
        TODO("Not yet implemented")
    }
}