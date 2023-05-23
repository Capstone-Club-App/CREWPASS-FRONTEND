package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentRVAdapter
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.ScrapGetAllResult
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.ScrapService
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.getResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.databinding.ActivityPersonalScrapBinding

class PersonalScrapActivity : AppCompatActivity(), ScrapGetAllResult {
    lateinit var binding: ActivityPersonalScrapBinding
    lateinit var context : Context

    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    var scrap_list = ArrayList<Recruitment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalScrapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "스크랩 목록"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    override fun onResume() {
        super.onResume()
        getScrap()
    }

    // 스크랩 목록 가져오기
    fun getScrap(){
        val scrapService = ScrapService()
        scrapService.setScrapGetAllResult(this)
        scrapService.getScrap(logined_id)
    }

    override fun scrapGetAllSuccess(code: Int, data: ArrayList<getResult>) {
        recruitmentRVAdapter = RecruitmentRVAdapter(data)
        binding.announcementListRv.adapter = recruitmentRVAdapter
        binding.announcementListRv.layoutManager = LinearLayoutManager(context)
        recruitmentRVAdapter.setItemClickListener(object :
            RecruitmentRVAdapter.OnItemClickListener {
            override fun onItemClick(recruitment: getResult) {
                val intent = Intent(context, RecruitmentDetailActivity::class.java)
                intent.putExtra("scrap", true)
                startActivity(intent) // 지원서 작성으로 이동
            }
        })
    }

    override fun scrapGetAllFailure(code: Int) {
        Log.d("스크랩 목록 가져오기 실패", "")
    }


}