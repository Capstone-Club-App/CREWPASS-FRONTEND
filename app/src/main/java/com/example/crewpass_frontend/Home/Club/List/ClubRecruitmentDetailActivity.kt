package com.example.crewpass_frontend.Home.Club.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Home.Club.List.Check.CheckApplicationActivity
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.DetailResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDetailResult
import com.example.crewpass_frontend.databinding.ActivityClubRecruitmentDetailBinding
import java.text.SimpleDateFormat

class ClubRecruitmentDetailActivity: AppCompatActivity(), RecruitmentGetDetailResult {
    lateinit var binding : ActivityClubRecruitmentDetailBinding
    lateinit var context: Context
    var question_id : Int = -1
    var crew_name : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubRecruitmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        initActionBar()

        val recruitment_id = intent.getIntExtra("recruitment_id", -1)
        if (recruitment_id != -1){
            val recruitmentAllService = RecruitmentAllService()
            recruitmentAllService.setRecruitmentGetDetailResultt(this)
            recruitmentAllService.getRecruitmentDetail(recruitment_id)
        }

        binding.btnCheckApplication.setOnClickListener {
            val intent = Intent(this, CheckApplicationActivity::class.java)
            intent.putExtra("question_id", question_id)
            intent.putExtra("crew_name", crew_name)
            startActivity(intent) // 지원서 작성으로 이동
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    override fun recruitmentGetDetailSuccess(code: Int, data: DetailResult) {
        Glide.with(context).load(data.crew_profile)
            .circleCrop()
            .into(binding.imgProfile)
        binding.txtClubName.text = data.crew_name
//        binding.itemAnnounceDetail.text = data.content
        var sdf = SimpleDateFormat("yyyy.MM.dd HH:mm")
        var date = sdf.format(data.register_time)
        binding.txtDateTime.text = date

        binding.edittextTitle.text = data.title
        binding.edittextContent.text = data.content

        question_id = data.question_id
        crew_name = data.crew_name
    }

    override fun recruitmentGetDetailFailure(code: Int) {
        Log.d("모집글 상세 정보 불러오기 실패", "")
    }
}