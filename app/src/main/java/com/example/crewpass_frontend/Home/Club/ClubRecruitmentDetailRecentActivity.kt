package com.example.crewpass_frontend.Home.Club

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Home.Club.List.Check.CheckApplicationActivity
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.DetailResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDetailResult
import com.example.crewpass_frontend.databinding.ActivityClubRecruitmentDetailBinding
import com.example.crewpass_frontend.databinding.ActivityClubRecruitmentDetailRecentBinding
import java.text.SimpleDateFormat

class ClubRecruitmentDetailRecentActivity: AppCompatActivity(), RecruitmentGetDetailResult {
    lateinit var binding : ActivityClubRecruitmentDetailRecentBinding
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubRecruitmentDetailRecentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        initActionBar()

        val recruitment_id = intent.getIntExtra("recruitment_id", -1)
        if (recruitment_id != -1){
            val recruitmentAllService = RecruitmentAllService()
            recruitmentAllService.setRecruitmentGetDetailResultt(this)
            recruitmentAllService.getRecruitmentDetail(recruitment_id)
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
        val content = data.content.replace("\\n", "\n")
        binding.edittextContent.text = content

        Glide.with(this).load(data.image).into(binding.imageViewImage)
    }

    override fun recruitmentGetDetailFailure(code: Int) {
        Log.d("모집글 상세 정보 불러오기 실패", "")
    }
}