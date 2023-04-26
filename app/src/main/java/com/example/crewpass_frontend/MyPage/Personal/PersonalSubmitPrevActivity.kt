package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalSubmitPrevBinding
import java.sql.Timestamp
import java.util.Date

class PersonalSubmitPrevActivity:AppCompatActivity() {
    lateinit var binding: ActivityPersonalSubmitPrevBinding
    lateinit var context: Context
    var application_list = ArrayList<Application>()
    lateinit var applicationRVAdapter: ApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSubmitPrevBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
        initRecyclerView()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원 이력"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    fun initRecyclerView() {

        // 마감임박순 데이터 가져오기
        // 임의값
        var timestamp = Timestamp(Date().time)
        application_list.apply {
            add(Application(1,timestamp,
                "...", "...", "11", " ", " ", " ", " ",
            3, 3, 2, 1, 1, 1, 1,
                1, 1
            ))
            add(Application(1,timestamp,
                "...", "...", "11", " ", " ", " ", " ",
                3, 3, 2, 1, 1, 1, 1,
                1, 1
            ))

            applicationRVAdapter = ApplicationRVAdapter(application_list, context)
            binding.announcementListRv.adapter = applicationRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            applicationRVAdapter.setItemClickListener(object :
                ApplicationRVAdapter.OnItemClickListener {
                override fun onItemClick(application: Application) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }
}