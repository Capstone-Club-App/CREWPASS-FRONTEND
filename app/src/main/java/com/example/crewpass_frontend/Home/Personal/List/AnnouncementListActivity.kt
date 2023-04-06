package com.example.crewpass_frontend.Home.Personal.List

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.databinding.ActivityAnnouncementListBinding

class AnnouncementListActivity : AppCompatActivity(){
    lateinit var binding : ActivityAnnouncementListBinding
    var recent_list = ArrayList<Announcement>()
    var imminent_list = ArrayList<Announcement>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list_state = intent.getStringExtra("list_state")






        initActionBar(list_state!!)
        initRecyclerView(list_state)

    }

    fun initActionBar(list_state : String){
        if(list_state.equals("recent")){
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "최신순"
        }
        else{
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "최신순"
        }
    }

    fun initRecyclerView(list_state: String){
        if(list_state.equals("recent")){
            // 최신순 데이터 가져오기
            //임의값
            recent_list.add(Announcement("최신 동아리1", "제목1", "내용1"))
            recent_list.add(Announcement("최신 동아리2", "제목2", "내용2"))
            recent_list.add(Announcement("최신 동아리3", "제목3", "내용3"))
            recent_list.add(Announcement("최신 동아리4", "제목4", "내용4"))
            recent_list.add(Announcement("최신 동아리5", "제목5", "내용5"))
            recent_list.add(Announcement("최신 동아리6", "제목6", "내용6"))
            recent_list.add(Announcement("최신 동아리7", "제목7", "내용7"))

            val announcementRVAdapter = AnnouncementRVAdapter(recent_list)
            binding.announcementListRv.adapter = announcementRVAdapter
            binding.announcementListRv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        else{
            // 마감임박순 데이터 가져오기
            // 임의값
            imminent_list.add(Announcement("임박 동아리1", "제목1", "내용1"))
            imminent_list.add(Announcement("임박 동아리2", "제목2", "내용2"))
            imminent_list.add(Announcement("임박 동아리3", "제목3", "내용3"))
            imminent_list.add(Announcement("임박 동아리4", "제목4", "내용4"))
            imminent_list.add(Announcement("임박 동아리5", "제목5", "내용5"))
            val announcementRVAdapter = AnnouncementRVAdapter(imminent_list)
            binding.announcementListRv.adapter = announcementRVAdapter
            binding.announcementListRv.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }
}