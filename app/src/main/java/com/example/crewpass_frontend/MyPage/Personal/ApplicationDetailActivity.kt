package com.example.crewpass_frontend.MyPage.Personal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationData
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationService
import com.example.crewpass_frontend.databinding.ActivityApplicationDetailBinding

class ApplicationDetailActivity: AppCompatActivity(), ApplicationGetResult {
    lateinit var binding : ActivityApplicationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application_id = intent.getIntExtra("application_id", -1)
        if(application_id != -1)
            getApplicationDetail(application_id)

        binding.btnClose.setOnClickListener {
            finish()
        }

        initActionBar()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 상세"
        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    fun getApplicationDetail(application_id : Int){
        val applicationService = ApplicationService()
        applicationService.setApplicationGetResult(this)
        applicationService.getApplication(application_id)
    }

    override fun applicationGetSuccess(code: Int, data: ArrayList<ApplicationData>) {
        binding.txtItemQuestion1.text = data[0].question1
        binding.txtItemMarginTextNum.text = data[0].question1Limit.toString() + "자"
        binding.edittextItemAnswer.text = data[0].answer1
        binding.txtItemCurrentTextNum.text = data[0].answer1Count.toString()

        binding.txtItemQuestion2.text = data[0].question2
        binding.txtItemMarginTextNum2.text = data[0].question2Limit.toString() + "자"
        binding.edittextItemAnswer2.text = data[0].answer2
        binding.txtItemCurrentTextNum2.text = data[0].answer2Count.toString()

        binding.txtItemQuestion3.text = data[0].question3
        binding.txtItemMarginTextNum3.text = data[0].question3Limit.toString() + "자"
        binding.edittextItemAnswer3.text = data[0].answer3
        binding.txtItemCurrentTextNum3.text = data[0].answer3Count.toString()

        if(data[0].question4.equals("")){
            binding.layoutQuestion4.visibility = View.VISIBLE
            binding.txtItemQuestion4.text = data[0].question4
            binding.txtItemMarginTextNum4.text = data[0].question4Limit.toString() + "자"
            binding.edittextItemAnswer4.text = data[0].answer4
            binding.txtItemCurrentTextNum4.text = data[0].answer4Count.toString()
        }

        if(data[0].question5.equals("")){
            binding.layoutQuestion5.visibility = View.VISIBLE
            binding.txtItemQuestion5.text = data[0].question5
            binding.txtItemMarginTextNum5.text = data[0].question5Limit.toString() + "자"
            binding.edittextItemAnswer5.text = data[0].answer5
            binding.txtItemCurrentTextNum5.text = data[0].answer5Count.toString()
        }

        if(data[0].question6.equals("")){
            binding.layoutQuestion6.visibility = View.VISIBLE
            binding.txtItemQuestion6.text = data[0].question4
            binding.txtItemMarginTextNum6.text = data[0].question6Limit.toString() + "자"
            binding.edittextItemAnswer6.text = data[0].answer6
            binding.txtItemCurrentTextNum6.text = data[0].answer6Count.toString()
        }

        if(data[0].question7.equals("")){
            binding.layoutQuestion7.visibility = View.VISIBLE
            binding.txtItemQuestion7.text = data[0].question7
            binding.txtItemMarginTextNum7.text = data[0].question7Limit.toString() + "자"
            binding.edittextItemAnswer7.text = data[0].answer7
            binding.txtItemCurrentTextNum7.text = data[0].answer7Count.toString()
        }

    }

    override fun applicationGetFailure(code: Int) {
        Log.d("지원서 상세 가져오기 실패", "")
    }
}