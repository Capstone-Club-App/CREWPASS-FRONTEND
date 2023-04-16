package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Question
import com.example.crewpass_frontend.Home.Personal.List.AnswerList.QuestionRVAdapter
import com.example.crewpass_frontend.databinding.ActivitySubmitAnnouncementBinding

class SubmitAnnouncementActivity : AppCompatActivity(){ // 지원서 작성
    lateinit var binding: ActivitySubmitAnnouncementBinding
    lateinit var questionRVAdapter: QuestionRVAdapter
    lateinit var context : Context
    lateinit var questions : Question
    var question_list = ArrayList<String>()
    var question_margin_list = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        initActionBar()
        initRecyclerView()

        binding.btnSubmit.setOnClickListener {
            finish()
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 작성"

    }

    fun initRecyclerView(){
        questions = Question(1, "질문1", "질문2", "질문3","질문4", "질문5", "질문6", "질문7",
            100, 100, 200, 200, 300, 300, 500)
        question_list.apply {

            question_list.add(questions.question1)
            question_list.add(questions.question2)
            question_list.add(questions.question3)
            question_list.add(questions.question4)
            question_list.add(questions.question5)
            question_list.add(questions.question6)
            question_list.add(questions.question7)

            question_margin_list.add(questions.question1_limit)
            question_margin_list.add(questions.question2_limit)
            question_margin_list.add(questions.question3_limit)
            question_margin_list.add(questions.question4_limit)
            question_margin_list.add(questions.question5_limit)
            question_margin_list.add(questions.question6_limit)
            question_margin_list.add(questions.question7_limit)

            questionRVAdapter = QuestionRVAdapter(question_list, question_margin_list)
            binding.questionListRv.adapter = questionRVAdapter
            binding.questionListRv.layoutManager = LinearLayoutManager(context)
        }
    }
}