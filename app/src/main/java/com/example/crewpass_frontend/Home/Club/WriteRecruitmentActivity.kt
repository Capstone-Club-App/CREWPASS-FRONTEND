package com.example.crewpass_frontend.Home.Club

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityWriteAnnouncementBinding

class WriteRecruitmentActivity: AppCompatActivity() {
    lateinit var binding: ActivityWriteAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnWriteQuestion.setOnClickListener {
            val intent = Intent(this, WriteQuestionActivity::class.java)
            startActivity(intent)

        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 작성"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}