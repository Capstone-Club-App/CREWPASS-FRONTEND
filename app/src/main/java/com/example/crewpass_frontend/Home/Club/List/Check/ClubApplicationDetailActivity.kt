package com.example.crewpass_frontend.Home.Club.List.Check

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityClubApplicationDetailBinding

class ClubApplicationDetailActivity :AppCompatActivity() {
    lateinit var binding: ActivityClubApplicationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubApplicationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 조회"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}