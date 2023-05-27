package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Home.Club.List.Check.ClubApplicationDetailActivity
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationData
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationGetResult
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationService
import com.example.crewpass_frontend.databinding.ActivityPersonalChooseAnnouncementBinding
import kotlin.collections.ArrayList

class AIClubChooseApplicationActivity : AppCompatActivity(), ApplicationGetResult {
    lateinit var binding: ActivityPersonalChooseAnnouncementBinding
    lateinit var context: Context
    var application_list = ArrayList<Application>()
    var question_id = -1
    lateinit var aiApplicationRVAdapter: AIApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityPersonalChooseAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        question_id = intent.getIntExtra("question_id", -1)
        if (question_id != -1)
            getApplicaitonList()


        binding.btnNext.setOnClickListener {
            val intent = Intent(this, AnalyzedApplicationActivity::class.java)
            intent.putExtra("application_id", aiApplicationRVAdapter.application_id_selected)
            intent.putExtra("user_name", aiApplicationRVAdapter.user_name)
            startActivity(intent)
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "동아리 모집글 현황"
        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    // 지원서 목록 가져오기
    fun getApplicaitonList() {
        val applicationService = ApplicationService()
        applicationService.setApplicationGetResult(this)
        applicationService.getApplication(question_id)
    }

    override fun applicationGetSuccess(code: Int, data: ArrayList<ApplicationData>) {
        initRecyclerView(data)
    }

    override fun applicationGetFailure(code: Int) {
        Log.d("지원서 조회 실패", "")
    }

    fun initRecyclerView(data: ArrayList<ApplicationData>) {
        aiApplicationRVAdapter = AIApplicationRVAdapter(data)
        binding.announcementListRv.adapter = aiApplicationRVAdapter
        binding.announcementListRv.layoutManager = LinearLayoutManager(context)
        aiApplicationRVAdapter.setItemClickListener(object :
            AIApplicationRVAdapter.OnItemClickListener {
            override fun onItemClick(application: ApplicationData) {
                val intent = Intent(context, ClubApplicationDetailActivity::class.java)
                intent.putExtra("application_id", application.application_id)
                startActivity(intent)
            }
        })
    }
}