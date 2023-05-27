package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentDetailActivity
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentData
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentGetResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentService
import com.example.crewpass_frontend.databinding.ActivityAiClubChooseRecruitmentBinding


class AIClubChooseRecruitmentActivity : AppCompatActivity(), RecruitmentGetResult {
    lateinit var binding: ActivityAiClubChooseRecruitmentBinding
    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAiClubChooseRecruitmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, AIClubChooseApplicationActivity::class.java)
            intent.putExtra("question_id" , recruitmentRVAdapter.question_id_selected)
            startActivity(intent)
        }

    }

    fun initActionBar() {

        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "동아리 모집글 현황"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}

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
            recruitmentRVAdapter = RecruitmentRVAdapter(result)
            binding.recruitmentListRv.adapter = recruitmentRVAdapter
            binding.recruitmentListRv.layoutManager = LinearLayoutManager(context)
            recruitmentRVAdapter.setItemClickListener(object :
                RecruitmentRVAdapter.OnItemClickListener{
                override fun onItemClick(recruitment: RecruitmentData) {
                    val intent = Intent(context, ClubRecruitmentDetailActivity::class.java)
                    intent.putExtra("recruitment_id", recruitment.recruitment_id)
                    startActivity(intent) // 상세보기
                }
            })
        }
    }

    override fun clubRecruitmentGetSuccess(code: Int, data: ArrayList<RecruitmentData>) {
        Log.d("동아리 모집글 정보 불러오기 성공", "")
        initRecyclerView_my(data)
    }

    override fun clubRecruitmentGetFailure(code: Int) {
        Log.d("동아리 모집글 정보 불러오기 실패", "")
    }
}