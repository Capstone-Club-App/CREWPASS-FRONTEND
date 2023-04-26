package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Data.Question
import com.example.crewpass_frontend.Home.Personal.List.AnswerList.QuestionEditRVAdapter
import com.example.crewpass_frontend.Home.Personal.List.AnswerList.QuestionRVAdapter
import com.example.crewpass_frontend.databinding.ActivityRecruitmentEditBinding
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class RecruitmentEditActivity: AppCompatActivity() {
    lateinit var binding : ActivityRecruitmentEditBinding
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
        binding = ActivityRecruitmentEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()

        binding.btnDone.setOnClickListener {
            finish()
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 수정"

    }

    fun initRecyclerView(){
        questions = Question(1, "질문1", "질문2", "질문3","질문4", "질문5", "질문6", "질문7",
            100, 100, 200, 200, 300, 300, 500)

        var timestamp = Timestamp(Date().time)
        application = Application(1,timestamp,
            "...", "...", "11", " ", " ", " ", " ",
            3, 3, 2, 1, 1, 1, 1,
            1, 1
        )
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

            answer_list.add(application.answer1)
            answer_list.add(application.answer2)
            answer_list.add(application.answer3)
            answer_list.add(application.answer4)
            answer_list.add(application.answer5)
            answer_list.add(application.answer6)
            answer_list.add(application.answer7)


            questionEditRVAdapter = QuestionEditRVAdapter(question_list, question_margin_list, answer_list, answer_count_list)
            binding.questionListRv.adapter = questionEditRVAdapter
            binding.questionListRv.layoutManager = LinearLayoutManager(context)
        }
    }
}