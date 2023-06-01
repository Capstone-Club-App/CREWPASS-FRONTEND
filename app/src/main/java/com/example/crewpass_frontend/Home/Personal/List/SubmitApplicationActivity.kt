package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Question
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationPostResult
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationService
import com.example.crewpass_frontend.Retrofit.Personal.Application.QuestionData
import com.example.crewpass_frontend.Retrofit.Personal.Application.QuestionGetResult
import com.example.crewpass_frontend.databinding.ActivitySubmitAnnouncementBinding

class SubmitApplicationActivity : AppCompatActivity(), QuestionGetResult, ApplicationPostResult{ // 지원서 작성
    lateinit var binding: ActivitySubmitAnnouncementBinding
    lateinit var context : Context
    var limit1 = 0
    var limit2 = 0
    var limit3 = 0
    var limit4 = 0
    var limit5 = 0
    var limit6 = 0
    var limit7 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmitAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var question_id = intent.getIntExtra("question_id", -1)
        Log.d("question_id : $question_id", "")

        initActionBar()
        getQuestion(question_id)

        // 1번 답변
        binding.edittextItemAnswer.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer.length() > limit1){
                    binding.edittextItemAnswer.setText(maxText)
                    binding.edittextItemAnswer.setSelection(binding.edittextItemAnswer.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum.setText(binding.edittextItemAnswer.text.length.toString())
            }

        })

        // 2번 답변
        binding.edittextItemAnswer2.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer2.length() > limit2){
                    binding.edittextItemAnswer2.setText(maxText)
                    binding.edittextItemAnswer2.setSelection(binding.edittextItemAnswer2.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum2.setText(binding.edittextItemAnswer2.text.length.toString())
            }

        })

        // 3번 답변
        binding.edittextItemAnswer3.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer3.length() > limit3){
                    binding.edittextItemAnswer3.setText(maxText)
                    binding.edittextItemAnswer3.setSelection(binding.edittextItemAnswer3.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum3.setText(binding.edittextItemAnswer3.text.length.toString())
            }
        })

        // 4번 답변
        binding.edittextItemAnswer4.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer4.length() > limit4){
                    binding.edittextItemAnswer4.setText(maxText)
                    binding.edittextItemAnswer4.setSelection(binding.edittextItemAnswer4.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum4.setText(binding.edittextItemAnswer4.text.length.toString())
            }

        })

        // 5번 답변
        binding.edittextItemAnswer5.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer5.length() > limit5){
                    binding.edittextItemAnswer5.setText(maxText)
                    binding.edittextItemAnswer5.setSelection(binding.edittextItemAnswer5.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum5.setText(binding.edittextItemAnswer5.text.length.toString())
            }

        })

        // 6번 답변
        binding.edittextItemAnswer6.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer6.length() > limit6){
                    binding.edittextItemAnswer6.setText(maxText)
                    binding.edittextItemAnswer6.setSelection(binding.edittextItemAnswer6.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum6.setText(binding.edittextItemAnswer6.text.length.toString())
            }
        })

        // 7번 답변
        binding.edittextItemAnswer7.addTextChangedListener (object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                maxText = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edittextItemAnswer7.length() > limit7){
                    binding.edittextItemAnswer7.setText(maxText)
                    binding.edittextItemAnswer7.setSelection(binding.edittextItemAnswer7.length())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.txtItemCurrentTextNum7.setText(binding.edittextItemAnswer7.text.length.toString())
            }

        })


        binding.btnSubmit.setOnClickListener {
            postApplication(question_id)
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 작성"

    }

    // 질문 가져오기
    fun getQuestion(question_id : Int){
        val applicationService = ApplicationService()
        applicationService.setQuestionGetResult(this)
        applicationService.getQuestion(question_id)
    }

    override fun questionGetSuccess(code: Int, data: QuestionData) {
        Log.d("", "question4Limit: ${data.question4Limit}, question4: ${data.question4}")
        binding.txtItemQuestion1.text = data.question1
        binding.txtItemMarginTextNum.text = data.question1Limit.toString() + "자"
        limit1 = data.question1Limit

        binding.txtItemQuestion2.text = data.question2
        binding.txtItemMarginTextNum2.text = data.question2Limit.toString() + "자"
        limit2 = data.question2Limit

        binding.txtItemQuestion3.text = data.question3
        binding.txtItemMarginTextNum3.text = data.question3Limit.toString() + "자"
        limit3 = data.question3Limit

        if(data.question4Limit != 0){
            binding.layoutQuestion4.visibility = View.VISIBLE
            binding.txtItemQuestion4.text = data.question4
            binding.txtItemMarginTextNum4.text = data.question4Limit.toString() + "자"
            limit4 = data.question4Limit!!
        }

        if(data.question5Limit != 0){
            binding.layoutQuestion5.visibility = View.VISIBLE
            binding.txtItemQuestion5.text = data.question5
            binding.txtItemMarginTextNum5.text = data.question5Limit.toString() + "자"
            limit5 = data.question5Limit!!
        }

        if(data.question6Limit != 0){
            binding.layoutQuestion6.visibility = View.VISIBLE
            binding.txtItemQuestion6.text = data.question6
            binding.txtItemMarginTextNum6.text = data.question6Limit.toString() + "자"
            limit6 = data.question6Limit!!
        }

        if(data.question7Limit != 0){
            binding.layoutQuestion7.visibility = View.VISIBLE
            binding.txtItemQuestion7.text = data.question7
            binding.txtItemMarginTextNum7.text = data.question7Limit.toString() + "자"
            limit7 = data.question7Limit!!
        }
    }

    override fun questionGetFailure(code: Int) {
        Log.d("질문 가져오기 실패" , "")
    }

    // 지원서 제출하기
    fun postApplication(question_id: Int){
        val applicationService = ApplicationService()
        applicationService.setApplicationPostResult(this)
        applicationService.postApplication(
            logined_id,
            question_id,
            binding.edittextItemAnswer.text.toString(),
            binding.edittextItemAnswer2.text.toString(),
            binding.edittextItemAnswer3.text.toString(),
            binding.edittextItemAnswer4.text.toString(),
            binding.edittextItemAnswer5.text.toString(),
            binding.edittextItemAnswer6.text.toString(),
            binding.edittextItemAnswer7.text.toString(),
            binding.txtItemCurrentTextNum.text.toString().toInt(),
            binding.txtItemCurrentTextNum2.text.toString().toInt(),
            binding.txtItemCurrentTextNum3.text.toString().toInt(),
            binding.txtItemCurrentTextNum4.text.toString().toInt(),
            binding.txtItemCurrentTextNum5.text.toString().toInt(),
            binding.txtItemCurrentTextNum6.text.toString().toInt(),
            binding.txtItemCurrentTextNum7.text.toString().toInt(),
        )
    }

    override fun applicationPostSuccess(code: Int) {
        Log.d("지원서 제출 성공", "")
        finish()
    }

    override fun applicationPostFailure(code: Int) {
        Log.d("지원서 제출 실패", "")
    }

}