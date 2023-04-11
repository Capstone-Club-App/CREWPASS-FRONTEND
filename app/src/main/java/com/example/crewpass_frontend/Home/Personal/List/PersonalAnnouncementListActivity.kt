package com.example.crewpass_frontend.Home.Personal.List

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.databinding.ActivityPersonalAnnouncementListBinding

class PersonalAnnouncementListActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalAnnouncementListBinding
    lateinit var announcementRVAdapter: AnnouncementRVAdapter
    var recent_list = ArrayList<Announcement>()
    var imminent_list = ArrayList<Announcement>()
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
        } else {
            binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
            binding.innerPageTop.appbarPageNameLeftTv.text = "마감임박순"
        }
    }

    fun initRecyclerView(list_state: String) {
        if (list_state.equals("recent")) {
            // 최신순 데이터 가져오기
            //임의값
            recent_list.apply {
                add(Announcement("최신 동아리1", "제목1", "내용1"))
                add(Announcement("최신 동아리2", "제목2", "내용2"))
                add(Announcement("최신 동아리3", "제목3", "내용3"))
                add(Announcement("최신 동아리4", "제목4", "내용4"))
                add(Announcement("최신 동아리5", "제목5", "내용5"))
                add(Announcement("최신 동아리6", "제목6", "내용6"))
                add(Announcement("최신 동아리7", "제목7", "내용7"))

                announcementRVAdapter = AnnouncementRVAdapter(recent_list)
                binding.announcementListRv.adapter = announcementRVAdapter
                binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            }
        } else {
            // 마감임박순 데이터 가져오기
            // 임의값
            imminent_list.apply {
                add(Announcement("임박 동아리1", "제목1", "내용1"))
                add(Announcement("임박 동아리2", "제목2", "내용2"))
                add(Announcement("임박 동아리3", "제목3", "내용3"))
                add(Announcement("임박 동아리4", "제목4", "내용4"))
                add(Announcement("임박 동아리5", "제목5", "내용5"))
                add(Announcement("임박 동아리6", "제목6", "내용6"))
                add(Announcement("임박 동아리7", "제목7", "내용7"))

                announcementRVAdapter = AnnouncementRVAdapter(imminent_list)
                binding.announcementListRv.adapter = announcementRVAdapter
                binding.announcementListRv.layoutManager = LinearLayoutManager(context)
                announcementRVAdapter.setItemClickListener(object :
                    AnnouncementRVAdapter.OnItemClickListener {
                    override fun onItemClick(announcement: Announcement) {

                    }
                })
            }
        }
    }
}
