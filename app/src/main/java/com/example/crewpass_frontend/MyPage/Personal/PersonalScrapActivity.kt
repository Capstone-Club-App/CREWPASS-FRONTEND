package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentRVAdapter
import com.example.crewpass_frontend.databinding.ActivityPersonalScrapBinding

class PersonalScrapActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalScrapBinding
    lateinit var context : Context

    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    var scrap_list = ArrayList<Recruitment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalScrapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
        initRecyclerView()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "스크랩 목록"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    fun initRecyclerView() {

        // 마감임박순 데이터 가져오기
        // 임의값
        scrap_list.apply {
            add(Recruitment("임박 동아리1", "제목1", "내용1"))
            add(Recruitment("임박 동아리2", "제목2", "내용2"))
            add(Recruitment("임박 동아리3", "제목3", "내용3"))
            add(Recruitment("임박 동아리4", "제목4", "내용4"))
            add(Recruitment("임박 동아리5", "제목5", "내용5"))
            add(Recruitment("임박 동아리6", "제목6", "내용6"))
            add(Recruitment("임박 동아리7", "제목7", "내용7"))

            recruitmentRVAdapter = RecruitmentRVAdapter(scrap_list)
            binding.announcementListRv.adapter = recruitmentRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            recruitmentRVAdapter.setItemClickListener(object :
                RecruitmentRVAdapter.OnItemClickListener {
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }

}