package com.example.crewpass_frontend.MyPage.Personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Announcement
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementRVAdapter
import com.example.crewpass_frontend.databinding.ActivityPersonalScrapBinding

class PersonalScrapActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalScrapBinding
    lateinit var context : Context

    lateinit var announcementRVAdapter: AnnouncementRVAdapter
    var scrap_list = ArrayList<Announcement>()

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
            add(Announcement("임박 동아리1", "제목1", "내용1"))
            add(Announcement("임박 동아리2", "제목2", "내용2"))
            add(Announcement("임박 동아리3", "제목3", "내용3"))
            add(Announcement("임박 동아리4", "제목4", "내용4"))
            add(Announcement("임박 동아리5", "제목5", "내용5"))
            add(Announcement("임박 동아리6", "제목6", "내용6"))
            add(Announcement("임박 동아리7", "제목7", "내용7"))

            announcementRVAdapter = AnnouncementRVAdapter(scrap_list)
            binding.announcementListRv.adapter = announcementRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            announcementRVAdapter.setItemClickListener(object :
                AnnouncementRVAdapter.OnItemClickListener {
                override fun onItemClick(announcement: Announcement) {
                    val intent = Intent(context, AnnouncementDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }

}