package com.example.crewpass_frontend.Home.Personal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Announcement
import com.example.crewpass_frontend.Home.HomeImminentRVAdapter
import com.example.crewpass_frontend.Home.HomeRecentRVAdapter
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.PersonalAnnouncementListActivity
import com.example.crewpass_frontend.databinding.FragmentFriendBinding
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding

class FragmentFriend : Fragment() {
    lateinit var binding: FragmentFriendBinding
    lateinit var homeRecentRVAdapter: HomeRecentRVAdapter
    lateinit var homeImminentRVAdapter: HomeImminentRVAdapter

    var recent_list = ArrayList<Announcement>()
    var imminent_list = ArrayList<Announcement>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater,container,false)


        binding.btnFriendRecent.setOnClickListener {
            val intent = Intent(activity, PersonalAnnouncementListActivity::class.java)
            intent.putExtra("list_state", "recent")
            startActivity(intent)
        }

        binding.btnFriendImminent.setOnClickListener {
            val intent = Intent(activity, PersonalAnnouncementListActivity::class.java)
            intent.putExtra("list_state", "imminent")
            startActivity(intent)
        }

        initRecyclerView()

        return binding.root
    }


    fun initRecyclerView(){
        recent_list.apply {
            add(Announcement("친목 최신 동아리1", "제목1", "내용1"))
            add(Announcement("친목 최신 동아리2", "제목2", "내용2"))

            homeRecentRVAdapter = HomeRecentRVAdapter(recent_list)
            binding.friendRecentRv.adapter = homeRecentRVAdapter
            binding.friendRecentRv.layoutManager = LinearLayoutManager(context)
            homeRecentRVAdapter.setItemClickListener(object :
                HomeRecentRVAdapter.OnItemClickListener {
                override fun onItemClick(announcement: Announcement) {
                    val intent = Intent(context, AnnouncementDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }

        imminent_list.apply {
            add(Announcement("친목 마감임박 동아리1", "제목1", "내용1"))
            add(Announcement("친목 마감임박 동아리2", "제목2", "내용2"))

            homeImminentRVAdapter = HomeImminentRVAdapter(recent_list)
            binding.friendImminentRv.adapter = homeImminentRVAdapter
            binding.friendImminentRv.layoutManager = LinearLayoutManager(context)
            homeImminentRVAdapter.setItemClickListener(object :
                HomeImminentRVAdapter.OnItemClickListener {
                override fun onItemClick(announcement: Announcement) {
                    val intent = Intent(context, AnnouncementDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }
}