package com.example.crewpass_frontend.Home.Club.Recent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.Recruitment
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentDetailRecentActivity
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentTotalRVAdapter
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentDetailActivity
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.databinding.ActivityRecentListBinding

class ClubRecentListActivity : AppCompatActivity(), RecruitmentGetAllResult {
    lateinit var binding: ActivityRecentListBinding
    lateinit var recruitmentRVAdapter: ClubRecruitmentTotalRVAdapter
    var recent_list = ArrayList<Recruitment>()
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()

    }

    fun initActionBar() {

        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "동아리 모집글 현황"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}

    }

    override fun onResume() {
        super.onResume()
        getRecruitment_total()
    }



    override fun recruitmentGetAllFailure(code: Int) {
        Log.d("최신 모집글 정보 불러오기 실패", "")
    }

    // 전체 모집글 불러오기
    fun getRecruitment_total(){
        val recruitmentAllService = RecruitmentAllService()
        recruitmentAllService.setRecruitmentGetAllResult(this)
        recruitmentAllService.getRecruitmentAll("total")
    }

    override fun recruitmentGetAllSuccess(
        code: Int,
        data: ArrayList<Recruitment>
    ) {
        initRecyclerView_recent(data)
    }

    fun initRecyclerView_recent(result : ArrayList<Recruitment>){
        recruitmentRVAdapter = ClubRecruitmentTotalRVAdapter(result)
        binding.announcementListRv.adapter = recruitmentRVAdapter
        binding.announcementListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recruitmentRVAdapter.setItemClickListener(object :
            ClubRecruitmentTotalRVAdapter.OnItemClickListener{
            override fun onItemClick(recruitment: Recruitment) {
                val intent = Intent(context, ClubRecruitmentDetailRecentActivity::class.java)
                intent.putExtra("recruitment_id", recruitment.recruitment_id)
                startActivity(intent) // 상세보기
            }
        })
    }

//    fun initRecyclerView() {
//        // 최신순 데이터 가져오기
//        //임의값
//        recent_list.apply {
//            add(Recruitment("최신 동아리1", "제목1", "내용1"))
//            add(Recruitment("최신 동아리2", "제목2", "내용2"))
//            add(Recruitment("최신 동아리3", "제목3", "내용3"))
//            add(Recruitment("최신 동아리4", "제목4", "내용4"))
//            add(Recruitment("최신 동아리5", "제목5", "내용5"))
//            add(Recruitment("최신 동아리6", "제목6", "내용6"))
//            add(Recruitment("최신 동아리7", "제목7", "내용7"))
//            add(Recruitment("최신 동아리8", "제목8", "내용8"))
//            add(Recruitment("최신 동아리9", "제목9", "내용9"))
//            add(Recruitment("최신 동아리10", "제목10", "내용10"))
//
//            recruitmentRVAdapter = RecruitmentRVAdapter(recent_list)
//            binding.announcementListRv.adapter = recruitmentRVAdapter
//            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
//            recruitmentRVAdapter.setItemClickListener(object :
//                RecruitmentRVAdapter.OnItemClickListener {
//                override fun onItemClick(recruitment: Recruitment) {
//                    val intent = Intent(context, ClubRecruitmentDetailActivity::class.java)
//                    startActivity(intent) // 지원서 작성으로 이동
//                }
//            })
//        }
//
//    }
}