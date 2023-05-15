package com.example.crewpass_frontend.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentDetailRecentActivity
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentRVAdapter
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentTotalRVAdapter
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentDetailActivity
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentListActivity
import com.example.crewpass_frontend.Home.Club.Recent.ClubRecentListActivity

import com.example.crewpass_frontend.Home.Club.WriteRecruitmentActivity
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentData
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentGetResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ClubHomeFragment:Fragment(), RecruitmentGetResult, RecruitmentGetAllResult {
    lateinit var binding: FragmentClubHomeBinding

    lateinit var announcementRVAdapter: ClubRecruitmentRVAdapter
    lateinit var recruitmentTotalRVAdapter: ClubRecruitmentTotalRVAdapter
    lateinit var recruitmentAllService : RecruitmentAllService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubHomeBinding.inflate(inflater,container,false)

        recruitmentAllService = RecruitmentAllService()

        binding.btnWriteRecruitment.setOnClickListener {
            val intent = Intent(context, WriteRecruitmentActivity::class.java)
            startActivity(intent)

        }

        binding.btnMyRecruitment.setOnClickListener { // 게시한 모집글
            val intent = Intent(context, ClubRecruitmentListActivity::class.java)
            startActivity(intent)
        }

        binding.btnRecentRecruitment.setOnClickListener { // 동아리 모집글 현황
            val intent = Intent(context, ClubRecentListActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getRecruitment_my(logined_id)
    }


    // 내가 업로드한 모집글 불러오기
    fun getRecruitment_my(crew_id: Int){
        val recruitmentService = RecruitmentService()
        recruitmentService.setRecruitmentResult(this)
        recruitmentService.getRecruitment(crew_id)
    }

    fun initRecyclerView_my(result : ArrayList<RecruitmentData>){
        if (result.size != 0){
            announcementRVAdapter = ClubRecruitmentRVAdapter(result)
            binding.myRecruitmentRv.adapter = announcementRVAdapter
            binding.myRecruitmentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            announcementRVAdapter.setItemClickListener(object :
                ClubRecruitmentRVAdapter.OnItemClickListener{
                override fun onItemClick(recruitment: RecruitmentData) {
                    val intent = Intent(context, ClubRecruitmentDetailActivity::class.java)
                    intent.putExtra("recruitment_id", recruitment.recruitment_id)
                    startActivity(intent) // 상세보기
                }
            })
        }
        else{
            binding.txtRecruitmentNone.visibility = View.VISIBLE
        }
    }

    override fun clubRecruitmentGetSuccess(code: Int, data: ArrayList<RecruitmentData>) {
        initRecyclerView_my(data)
        getRecruitment_total()
    }

    override fun clubRecruitmentGetFailure(code: Int) {
        Log.d("동아리 모집글 정보 불러오기 실패", "")
    }

    // 전체 모집글 불러오기
    fun getRecruitment_total(){
        recruitmentAllService.setRecruitmentGetAllResult(this)
        recruitmentAllService.getRecruitmentAll("total")
    }
    fun initRecyclerView_recent(result : ArrayList<Recruitment>){
        recruitmentTotalRVAdapter = ClubRecruitmentTotalRVAdapter(result)
        binding.clubRecentRv.adapter = recruitmentTotalRVAdapter
        binding.clubRecentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recruitmentTotalRVAdapter.setItemClickListener(object :
            ClubRecruitmentTotalRVAdapter.OnItemClickListener{
            override fun onItemClick(recruitment: Recruitment) {
                val intent = Intent(context, ClubRecruitmentDetailRecentActivity::class.java)
                intent.putExtra("recruitment_id", recruitment.recruitment_id)
                startActivity(intent) // 상세보기
            }
        })
    }

    override fun recruitmentGetAllSuccess(
        code: Int,
        data: ArrayList<Recruitment>
    ) {
        initRecyclerView_recent(data)
    }


    override fun recruitmentGetAllFailure(code: Int) {
        Log.d("최신 모집글 정보 불러오기 실패", "")
    }

}

