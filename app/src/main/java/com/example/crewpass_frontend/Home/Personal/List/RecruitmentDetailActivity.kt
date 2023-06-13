package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.DetailResult
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentAllService
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetDetailResult
import com.example.crewpass_frontend.databinding.ActivityAnnouncementDetailBinding
import java.text.SimpleDateFormat

class RecruitmentDetailActivity : AppCompatActivity(), RecruitmentGetDetailResult {
    lateinit var binding: ActivityAnnouncementDetailBinding
    lateinit var context: Context
    var question_id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        initActionBar()

        val key = intent.getBooleanExtra("scrap", false)
        Log.d("Key : ", key.toString())
        if (key)
            binding.btnHeart.setBackgroundResource(R.drawable.img_heart_fill)

        val recruitment_id = intent.getIntExtra("recruitment_id", -1)
        if (recruitment_id != -1) {
            val recruitmentAllService = RecruitmentAllService()
            recruitmentAllService.setRecruitmentGetDetailResultt(this)
            recruitmentAllService.getRecruitmentDetail(recruitment_id)
        }

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, SubmitApplicationActivity::class.java)
            intent.putExtra("question_id", question_id)
            startActivity(intent) // 지원서 작성으로 이동
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    override fun recruitmentGetDetailSuccess(code: Int, data: DetailResult) {
        Glide.with(context).load(data.crew_profile)
            .circleCrop()
            .into(binding.imgProfile)
        binding.txtClubName.text = data.crew_name
//        binding.itemAnnounceDetail.text = data.content
        var sdf_d = SimpleDateFormat("yyyy-MM-dd")
        var date = sdf_d.format(data.deadLine)
        binding.txtDeadlineDate.text = date

        var sdf_t = SimpleDateFormat("HH:mm")
        val time = sdf_t.format(data.deadLine)
        binding.txtDeadlineTime.text = time

        binding.edittextTitle.text = data.title

        val content = data.content.replace("\\n", "\n")
        binding.edittextContent.text = content

        question_id = data.question_id

        Glide.with(this).load(data.image).into(binding.imageViewImage)
    }

    override fun recruitmentGetDetailFailure(code: Int) {
        Log.d("모집글 상세 정보 불러오기 실패", "")
    }
}