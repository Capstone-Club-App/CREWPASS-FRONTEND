package com.example.crewpass_frontend.MyPage.Club

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentRVAdapter
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentDetailActivity
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentData
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentGetResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentService
import com.example.crewpass_frontend.databinding.ActivityClubEditRecruitmentBinding

class ClubEditRecruitmentActivity  : AppCompatActivity(), RecruitmentGetResult {
    lateinit var binding: ActivityClubEditRecruitmentBinding
    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    var recent_list = ArrayList<Recruitment>()
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubEditRecruitmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
    }

    fun initActionBar() {

        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 목록"
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
            binding.announcementListRv.adapter = recruitmentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recruitmentRVAdapter.setItemClickListener(object :
                RecruitmentRVAdapter.OnItemClickListener{
                override fun onItemClick(recruitment: RecruitmentData) {
                    val intent = Intent(context, ClubRecruitmentDetailActivity::class.java)
                    intent.putExtra("recruitment_id", recruitment.recruitment_id)
                    startActivity(intent) // 상세보기
                }
            })
        }
//        else{
//            binding.txtRecruitmentNone.visibility = View.VISIBLE
//        }
    }

    override fun clubRecruitmentGetSuccess(code: Int, data: ArrayList<RecruitmentData>) {
        Log.d("동아리 모집글 정보 불러오기 성공", "")
        initRecyclerView_my(data)
    }

    override fun clubRecruitmentGetFailure(code: Int) {
        Log.d("동아리 모집글 정보 불러오기 실패", "")
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
//                    val intent = Intent(context, EditRecruitmentDetailActivity::class.java)
//                    startActivity(intent) // 지원서 작성으로 이동
//                }
//            })
//        }
//
//    }
}