package com.example.crewpass_frontend.AI.Personal

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.Personal.AI.AnalyzeApplicationService
import com.example.crewpass_frontend.Retrofit.Personal.AI.AnalyzeApplicationResult
import com.example.crewpass_frontend.databinding.ActivityPersonalPrepareInterviewBinding

class PersonalPrepareInterviewActivity : AppCompatActivity(), AnalyzeApplicationResult {
    lateinit var binding: ActivityPersonalPrepareInterviewBinding
    lateinit var progressDialog: AppCompatDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalPrepareInterviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var application_id = intent.getIntExtra("application_id", -1)
        Log.d("", "application_id : $application_id")

        if(application_id != -1){
            analyze_application(application_id)
        }

        initActionBar()
        progressON()

        binding.btnPdf.setOnClickListener {

        }
    }

    fun progressON(){
        progressDialog = AppCompatDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.dialog_loading)
        progressDialog.show()
        var img_loading_framge = progressDialog.findViewById<ImageView>(R.id.iv_frame_loading)
        var frameAnimation = img_loading_framge?.getBackground() as AnimationDrawable
        img_loading_framge?.post(object : Runnable{
            override fun run() {
                frameAnimation.start()
            }
        })
    }
    fun progressOFF(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss()
        }
    }

    fun analyze_application(application_id : Int){
        val analyzeApplicationService = AnalyzeApplicationService()
        analyzeApplicationService.setAnalyzeApplicationResult(this)
        analyzeApplicationService.analyzeApplication(application_id)

    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "면접 준비"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    override fun analyzeApplicationSuccess(
        code: Int,
        data: com.example.crewpass_frontend.Retrofit.Personal.AI.AnalyzeResult
    ) {
        progressOFF()
//        val questionList = data.interview
//        var question_string = ""
//        questionList.forEachIndexed { index, item ->
//            if(index != questionList.size - 1)
//                question_string += item + "<br>"
//            else
//                question_string += item
//        }
//        binding.edittextQuestion.text = Html.fromHtml(question_string)

        Log.d("interview size ::", data.interview.size.toString())
        Log.d("summary size ::", data.summary.size.toString())

        binding.edittextQuestion1Quesiton.text = data.interview[0]
        binding.edittextQuestion1Answer.text = data.summary[0]

        binding.edittextQuestion2Quesiton.text = data.interview[1]
        binding.edittextQuestion2Answer.text = data.summary[1]

        binding.edittextQuestion3Quesiton.text = data.interview[2]
        binding.edittextQuestion3Answer.text = data.summary[2]
    }

    override fun analyzeApplicationFailure(code: Int) {
        progressOFF()
        Log.d("신청서 분석 실패", "")
    }
}