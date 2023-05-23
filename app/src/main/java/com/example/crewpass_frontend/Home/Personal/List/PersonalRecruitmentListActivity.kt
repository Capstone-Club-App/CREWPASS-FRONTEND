package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Home.HomeImminentRVAdapter
import com.example.crewpass_frontend.Home.HomeRecentRVAdapter
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.ScrapGetAllResult
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.ScrapService
import com.example.crewpass_frontend.Retrofit.Personal.Scrap.getResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDeadlineResult
import com.example.crewpass_frontend.databinding.ActivityPersonalAnnouncementListBinding


class PersonalRecruitmentListActivity : AppCompatActivity(), RecruitmentGetAllResult,
    RecruitmentGetDeadlineResult, ScrapGetAllResult {
    lateinit var binding: ActivityPersonalAnnouncementListBinding
    lateinit var homeRecentRVAdapter: HomeRecentRVAdapter
    lateinit var homeImminentRVAdapter: HomeImminentRVAdapter

    var scrap_list = ArrayList<getResult>()
    lateinit var context: Context
    var list_state = ""
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalAnnouncementListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        list_state = intent.getStringExtra("list_state")!!
        type = intent.getStringExtra("type")!!
        Log.d("list_state  : $list_state, type : $type", "")


        initActionBar(list_state)

    }

    fun initActionBar(list_state: String) {
        if (list_state.equals("recent")) {
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "최신순"
            binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
        } else {
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "마감임박순"
            binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
        }
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
        scrap_list = data
        if(list_state.equals("recent"))
            getRecruitment_recent()
        else
            getRecruitment_imminent()
    }

    override fun scrapGetAllFailure(code: Int) {
        Log.d("스크랩 목록 가져오기 실패", "")
    }

    // 전체 최신 모집글 불러오기
    fun getRecruitment_recent() {
        val recruitmentAllService = RecruitmentAllService()
        recruitmentAllService.setRecruitmentGetAllResult(this)
        recruitmentAllService.getRecruitmentAll(type)
    }

    // 동아리 기본 마감임박 모집글 가져오기
    fun getRecruitment_imminent() {
        val recruitmentAllService = RecruitmentAllService()
        recruitmentAllService.setRecruitmentGetDeadlineResult(this)
        recruitmentAllService.getRecruitmentDeadline(type)
    }

    override fun recruitmentGetAllSuccess(
        code: Int,
        data: ArrayList<Recruitment>
    ) {
        initRecyclerView("recent", data)
    }

    override fun recruitmentGetAllFailure(code: Int) {
        Log.d("동아리 모집글 최신순 불러오기 실패", "")
    }

    override fun recruitmentGetDeadlineSuccess(
        code: Int,
        data: ArrayList<Recruitment>
    ) {
        initRecyclerView(list_state, data)
    }

    override fun recruitmentGetDeadlineFailure(code: Int) {
        Log.d("동아리 모집글 마감임박순 불러오기 실패", "")
    }

    fun initRecyclerView(
        list_state: String,
        data: ArrayList<Recruitment>
    ) {
        if (list_state.equals("recent")) {
            // 최신순 데이터 가져오기
            homeRecentRVAdapter = HomeRecentRVAdapter(data,scrap_list)
            binding.announcementListRv.adapter = homeRecentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            homeRecentRVAdapter.setItemClickListener(object :
                HomeRecentRVAdapter.OnItemClickListener{
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("recruitment_id", recruitment.recruitment_id)
                    startActivity(intent) // 상세보기
                }
            })

        } else {
            // 마감임박순 데이터 가져오기
            homeImminentRVAdapter = HomeImminentRVAdapter(data,scrap_list)
            binding.announcementListRv.adapter = homeImminentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            homeImminentRVAdapter.setItemClickListener(object :
                HomeImminentRVAdapter.OnItemClickListener{
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("recruitment_id", recruitment.recruitment_id)
                    startActivity(intent) // 상세보기
                }
            })
        }

    }


}
