package com.example.crewpass_frontend.MyPage.Club

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityEditRecruitmentDetailBinding

class EditRecruitmentDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityEditRecruitmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecruitmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnDone.setOnClickListener {
            finish()
        }
    }

    fun initActionBar() {

        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 수정"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}

    }
}