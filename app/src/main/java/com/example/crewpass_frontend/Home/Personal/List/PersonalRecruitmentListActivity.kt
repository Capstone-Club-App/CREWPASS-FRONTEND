package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDeadlineResult
import com.example.crewpass_frontend.databinding.ActivityPersonalAnnouncementListBinding

class PersonalRecruitmentListActivity : AppCompatActivity(), RecruitmentGetAllResult,
    RecruitmentGetDeadlineResult {
    lateinit var binding: ActivityPersonalAnnouncementListBinding
    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    var recent_list = ArrayList<Recruitment>()
    var imminent_list = ArrayList<Recruitment>()
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
        data: ArrayList<com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment>
    ) {
        initRecyclerView("recent", data)
    }

    override fun recruitmentGetAllFailure(code: Int) {
        Log.d("동아리 모집글 최신순 불러오기 실패", "")
    }

    override fun recruitmentGetDeadlineSuccess(
        code: Int,
        data: ArrayList<com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment>
    ) {
        initRecyclerView(list_state, data)
    }

    override fun recruitmentGetDeadlineFailure(code: Int) {
        Log.d("동아리 모집글 마감임박순 불러오기 실패", "")
    }

    fun initRecyclerView(
        list_state: String,
        data: ArrayList<com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment>
    ) {
        if (list_state.equals("recent")) {
            // 최신순 데이터 가져오기
            recruitmentRVAdapter = RecruitmentRVAdapter(recent_list)
            binding.announcementListRv.adapter = recruitmentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            recruitmentRVAdapter.setItemClickListener(object :
                RecruitmentRVAdapter.OnItemClickListener {
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    startActivity(intent)
                }
            })

        } else {
            // 마감임박순 데이터 가져오기
            // 임의값
            recruitmentRVAdapter = RecruitmentRVAdapter(imminent_list)
            binding.announcementListRv.adapter = recruitmentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            recruitmentRVAdapter.setItemClickListener(object :
                RecruitmentRVAdapter.OnItemClickListener {
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    startActivity(intent)
                }
            })
        }

    }


}
