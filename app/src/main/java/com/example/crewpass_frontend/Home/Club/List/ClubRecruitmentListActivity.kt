package com.example.crewpass_frontend.Home.Club.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentRVAdapter
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentData
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentGetResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentService
import com.example.crewpass_frontend.databinding.ActivityClubRecruitmentListBinding

class ClubRecruitmentListActivity : AppCompatActivity(), RecruitmentGetResult {
    lateinit var binding: ActivityClubRecruitmentListBinding
    lateinit var announcementRVAdapter: ClubRecruitmentRVAdapter
    var recent_list = ArrayList<Recruitment>()
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubRecruitmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()

    }

    override fun onResume() {
        super.onResume()
        getRecruitment_my(logined_id)
    }

    fun initActionBar() {

            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "최신순"
            binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}

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
            binding.announcementListRv.adapter = announcementRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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

    }

    override fun clubRecruitmentGetFailure(code: Int) {
        Log.d("동아리 모집글 정보 불러오기 실패", "")
    }
}
