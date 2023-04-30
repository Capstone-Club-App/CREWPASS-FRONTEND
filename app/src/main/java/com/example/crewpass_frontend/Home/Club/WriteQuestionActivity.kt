package com.example.crewpass_frontend.Home.Club

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.databinding.ActivityWriteQuestionBinding

class WriteQuestionActivity: AppCompatActivity() {
    var add_count = 4
    lateinit var binding: ActivityWriteQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnAddQuestion.setOnClickListener {
            add_count++
            if(add_count == 5){
                binding.txtQuestion5.visibility = View.VISIBLE
                binding.edittextQuestion5.visibility = View.VISIBLE
            }
            if(add_count == 6){
                binding.txtQuestion6.visibility = View.VISIBLE
                binding.edittextQuestion6.visibility = View.VISIBLE
            }
            if(add_count == 7){
                binding.txtQuestion7.visibility = View.VISIBLE
                binding.edittextQuestion7.visibility = View.VISIBLE

                binding.btnAddQuestion.visibility = View.GONE
            }
        }

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 질문 작성"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}