package com.example.crewpass_frontend.MyPage.Personal

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalEditInfoBinding

class PersonalEditInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalEditInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "기본정보 편집"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}