package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.databinding.ActivityPersonalAnnouncementListBinding

class PersonalRecruitmentListActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalAnnouncementListBinding
    lateinit var recruitmentRVAdapter: RecruitmentRVAdapter
    var recent_list = ArrayList<Recruitment>()
    var imminent_list = ArrayList<Recruitment>()
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalAnnouncementListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        var list_state = intent.getStringExtra("list_state")
        Log.d("list_state  : ", list_state!!)


        initActionBar(list_state)
        initRecyclerView(list_state)

    }

    fun initActionBar(list_state: String) {
        if (list_state.equals("recent")) {
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "최신순"
            binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
        } else {
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "마감임박순"
            binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
        }
    }

    fun initRecyclerView(list_state: String) {
        if (list_state.equals("recent")) {
            // 최신순 데이터 가져오기
            //임의값
            recent_list.apply {
                add(Recruitment("최신 동아리1", "제목1", "내용1"))
                add(Recruitment("최신 동아리2", "제목2", "내용2"))
                add(Recruitment("최신 동아리3", "제목3", "내용3"))
                add(Recruitment("최신 동아리4", "제목4", "내용4"))
                add(Recruitment("최신 동아리5", "제목5", "내용5"))
                add(Recruitment("최신 동아리6", "제목6", "내용6"))
                add(Recruitment("최신 동아리7", "제목7", "내용7"))
                add(Recruitment("최신 동아리8", "제목8", "내용8"))
                add(Recruitment("최신 동아리9", "제목9", "내용9"))
                add(Recruitment("최신 동아리10", "제목10", "내용10"))

                recruitmentRVAdapter = RecruitmentRVAdapter(recent_list)
                binding.announcementListRv.adapter = recruitmentRVAdapter
                binding.announcementListRv.layoutManager = LinearLayoutManager(context)
                recruitmentRVAdapter.setItemClickListener(object :
                    RecruitmentRVAdapter.OnItemClickListener {
                    override fun onItemClick(recruitment: Recruitment) {
                        val intent = Intent(context, RecruitmentDetailActivity::class.java)
                        startActivity(intent) // 지원서 작성으로 이동
                    }
                })
            }
        } else {
            // 마감임박순 데이터 가져오기
            // 임의값
            imminent_list.apply {
                add(Recruitment("임박 동아리1", "제목1", "내용1"))
                add(Recruitment("임박 동아리2", "제목2", "내용2"))
                add(Recruitment("임박 동아리3", "제목3", "내용3"))
                add(Recruitment("임박 동아리4", "제목4", "내용4"))
                add(Recruitment("임박 동아리5", "제목5", "내용5"))
                add(Recruitment("임박 동아리6", "제목6", "내용6"))
                add(Recruitment("임박 동아리7", "제목7", "내용7"))
                add(Recruitment("임박 동아리8", "제목8", "내용8"))
                add(Recruitment("임박 동아리9", "제목9", "내용9"))
                add(Recruitment("임박 동아리10", "제목10", "내용10"))

                recruitmentRVAdapter = RecruitmentRVAdapter(imminent_list)
                binding.announcementListRv.adapter = recruitmentRVAdapter
                binding.announcementListRv.layoutManager = LinearLayoutManager(context)
                recruitmentRVAdapter.setItemClickListener(object :
                    RecruitmentRVAdapter.OnItemClickListener {
                    override fun onItemClick(recruitment: Recruitment) {
                        val intent = Intent(context, RecruitmentDetailActivity::class.java)
                        startActivity(intent) // 지원서 작성으로 이동
                    }
                })
            }
        }
    }
}
