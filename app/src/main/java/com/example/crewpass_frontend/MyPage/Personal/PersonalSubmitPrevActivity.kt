package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationGetListResult
import com.example.crewpass_frontend.Retrofit.Personal.Application.ApplicationService
import com.example.crewpass_frontend.databinding.ActivityPersonalSubmitPrevBinding
import com.example.crewpass_frontend.Retrofit.Personal.Application.Application

class PersonalSubmitPrevActivity:AppCompatActivity(), ApplicationGetListResult {
    lateinit var binding: ActivityPersonalSubmitPrevBinding
    lateinit var context: Context

    lateinit var applicationRVAdapter: ApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSubmitPrevBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
        getApplicationList()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원 이력"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    fun getApplicationList(){
        val applicationService = ApplicationService()
        applicationService.setApplicationGetListResult(this)
        applicationService.getApplicationList(logined_id)
    }

    fun initRecyclerView(data : ArrayList<Application>) {
            applicationRVAdapter = ApplicationRVAdapter(data, context)
            binding.announcementListRv.adapter = applicationRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            applicationRVAdapter.setItemClickListener(object :
                ApplicationRVAdapter.OnItemClickListener {
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