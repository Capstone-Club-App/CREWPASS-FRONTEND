package com.example.crewpass_frontend.Home.Club

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityWriteAnnouncementBinding

class WriteAnnouncementActivity: AppCompatActivity() {
    lateinit var binding: ActivityWriteAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 작성"

    }
}