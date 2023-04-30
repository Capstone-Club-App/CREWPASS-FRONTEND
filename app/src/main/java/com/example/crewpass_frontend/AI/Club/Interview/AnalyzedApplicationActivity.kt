package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.databinding.ActivityAnalyzedApplicationBinding

class AnalyzedApplicationActivity: AppCompatActivity() {
    lateinit var binding : ActivityAnalyzedApplicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyzedApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnPdf.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 분석 결과"

        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }
}