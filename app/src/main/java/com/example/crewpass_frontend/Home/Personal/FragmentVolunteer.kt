package com.example.crewpass_frontend.Home.Personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Home.HomeImminentRVAdapter
import com.example.crewpass_frontend.Home.HomeRecentRVAdapter
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.PersonalRecruitmentListActivity
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentGetResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDeadlineResult
import com.example.crewpass_frontend.databinding.FragmentVolunteerBinding

class FragmentVolunteer : Fragment(), RecruitmentGetAllResult, RecruitmentGetDeadlineResult {
    lateinit var binding: FragmentVolunteerBinding

    lateinit var homeRecentRVAdapter: HomeRecentRVAdapter
    lateinit var homeImminentRVAdapter: HomeImminentRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVolunteerBinding.inflate(inflater,container,false)


        binding.btnVolunteerRecent.setOnClickListener {
            val intent = Intent(activity, PersonalRecruitmentListActivity::class.java)
            intent.putExtra("list_state", "recent")
            startActivity(intent)
        }

        binding.btnVolunteerImminent.setOnClickListener {
            val intent = Intent(activity, PersonalRecruitmentListActivity::class.java)
            intent.putExtra("list_state", "imminent")
            startActivity(intent)
        }

//        initRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getRecruitment_recent()
    }

    // 전체 최신 모집글 불러오기
    fun getRecruitment_recent(){
        val recruitmentAllService = RecruitmentAllService()
        recruitmentAllService.setRecruitmentGetAllResult(this)
        recruitmentAllService.getRecruitmentAll("봉사,사회활동")
    }
    fun initRecyclerView_recent(result : ArrayList<Recruitment>){
        homeRecentRVAdapter = HomeRecentRVAdapter(result)
        binding.volunteerRecentRv.adapter = homeRecentRVAdapter
        binding.volunteerRecentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeRecentRVAdapter.setItemClickListener(object :
            HomeRecentRVAdapter.OnItemClickListener{
            override fun onItemClick(recruitment: Recruitment) {
                val intent = Intent(context, RecruitmentDetailActivity::class.java)
                intent.putExtra("recruitment_id", recruitment.recruitment_id)
                startActivity(intent) // 상세보기
            }
        })
    }

    override fun recruitmentGetAllSuccess(code: Int, data: ArrayList<Recruitment>) {
        if(data.size != 0)
            initRecyclerView_recent(data)
        else
            binding.txtRecentNone.visibility = View.VISIBLE
        getRecruitment_imminent()
    }

    override fun recruitmentGetAllFailure(code: Int) {
        Log.d("동아리 모집글 최신순 불러오기 실패", "")
    }

    // 동아리 기본 마감임박 모집글 가져오기
    fun getRecruitment_imminent(){
        val recruitmentAllService = RecruitmentAllService()
        recruitmentAllService.setRecruitmentGetDeadlineResult(this)
        recruitmentAllService.getRecruitmentDeadline("봉사,사회활동")
    }
    fun initRecyclerView_imminent(result : ArrayList<Recruitment>){
        homeImminentRVAdapter = HomeImminentRVAdapter(result)
        binding.volunteerImminentRv.adapter = homeImminentRVAdapter
        binding.volunteerImminentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeImminentRVAdapter.setItemClickListener(object :
            HomeImminentRVAdapter.OnItemClickListener{
            override fun onItemClick(recruitment: Recruitment) {
                val intent = Intent(context, RecruitmentDetailActivity::class.java)
                intent.putExtra("recruitment_id", recruitment.recruitment_id)
                startActivity(intent) // 상세보기
            }
        })
    }

    override fun recruitmentGetDeadlineSuccess(code: Int, data: ArrayList<Recruitment>) {
        if(data.size != 0)
            initRecyclerView_imminent(data)
        else
            binding.txtImminentNone.visibility = View.VISIBLE
    }

    override fun recruitmentGetDeadlineFailure(code: Int) {
        Log.d("동아리 모집글 마감임박순 불러오기 실패", "")
    }
}