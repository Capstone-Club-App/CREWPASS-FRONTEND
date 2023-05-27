package com.example.crewpass_frontend.AI.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.MyPage.Personal.ApplicationDetailActivity
import com.example.crewpass_frontend.MyPage.Personal.ApplicationRVAdapter
import com.example.crewpass_frontend.Retrofit.Personal.Application.Application
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationGetListResult
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationService
import com.example.crewpass_frontend.databinding.ActivityPersonalChooseAnnouncementBinding
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class PersonalChooseApplicationActivity:AppCompatActivity(), ApplicationGetListResult {
    lateinit var binding: ActivityPersonalChooseAnnouncementBinding
    lateinit var context: Context
    lateinit var aiApplicationRVAdapter: AIApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        getApplicationList()

        binding = ActivityPersonalChooseAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, PersonalPrepareInterviewActivity::class.java)
            intent.putExtra("application_id", aiApplicationRVAdapter.application_id_selected)
            startActivity(intent)
        }
    }

    fun getApplicationList(){
        val applicationService = ApplicationService()
        applicationService.setApplicationGetListResult(this)
        applicationService.getApplicationList(logined_id)
    }

    fun initRecyclerView(data : ArrayList<Application>) {
        aiApplicationRVAdapter = AIApplicationRVAdapter(data)
        binding.announcementListRv.adapter = aiApplicationRVAdapter
        binding.announcementListRv.layoutManager = LinearLayoutManager(context)
        aiApplicationRVAdapter.setItemClickListener(object :
            AIApplicationRVAdapter.OnItemClickListener {
            override fun onItemClick(application: Application) {
                val intent = Intent(context, ApplicationDetailActivity::class.java)
                intent.putExtra("application_id", application.application_id)
                startActivity(intent) // 지원서 작성으로 이동
            }
        })

    }

    override fun applicationGetListSuccess(
        code: Int,
        data: ArrayList<Application>
    ) {
        Log.d("지원서 목록 가져오기 성공","")
        initRecyclerView(data)
    }

    override fun applicationGetListFailure(code: Int) {
        Log.d("지원서 목록 가져오기 실패","")
    }
}