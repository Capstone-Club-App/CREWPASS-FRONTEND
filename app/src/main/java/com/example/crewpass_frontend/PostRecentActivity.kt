package com.example.crewpass_frontend

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityMainBinding
import com.example.crewpass_frontend.databinding.ActivityRecentPostBinding

class PostRecentActivity:AppCompatActivity() {
    lateinit var binding: ActivityRecentPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionbar()
    }

    fun initActionbar(){
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_left_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)

        appBartext.text= "최신순"
        appBartext.visibility= View.VISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }
}