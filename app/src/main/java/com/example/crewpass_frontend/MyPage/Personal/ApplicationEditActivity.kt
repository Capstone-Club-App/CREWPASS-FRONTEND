package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Data.Question
import com.example.crewpass_frontend.Home.Personal.List.AnswerList.QuestionEditRVAdapter
import com.example.crewpass_frontend.databinding.ActivityApplicationEditBinding
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class ApplicationEditActivity: AppCompatActivity() {
    lateinit var binding : ActivityApplicationEditBinding
    lateinit var questionEditRVAdapter: QuestionEditRVAdapter
    lateinit var questions : Question
    lateinit var application: Application
    lateinit var context : Context
    var question_list = ArrayList<String>()
    var question_margin_list = ArrayList<Int>()
    var answer_list = ArrayList<String>()
    var answer_count_list = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 수정"

    }
}