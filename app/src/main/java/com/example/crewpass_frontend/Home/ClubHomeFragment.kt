package com.example.crewpass_frontend.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Announcement
import com.example.crewpass_frontend.Home.Club.ClubAnnouncementRVAdapter
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ClubHomeFragment:Fragment() {
    lateinit var binding: FragmentClubHomeBinding

    lateinit var announcementRVAdapter: ClubAnnouncementRVAdapter

    var club_recent_list = ArrayList<Announcement>()
    var club_imminent_list = ArrayList<Announcement>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubHomeBinding.inflate(inflater,container,false)

        initRecyclerView()

        return binding.root
    }

    fun initRecyclerView(){
        club_recent_list.apply {
            add(Announcement("최신 동아리1", "제목1", "내용1"))
            add(Announcement("최신 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubAnnouncementRVAdapter(club_recent_list)
            binding.clubRecentRv.adapter = announcementRVAdapter
            binding.clubRecentRv.layoutManager = LinearLayoutManager(context)
        }

        club_imminent_list.apply {
            add(Announcement("마감임박 동아리1","제목1", "내용1"))
            add(Announcement("마감임박 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubAnnouncementRVAdapter(club_imminent_list)
            binding.clubImminentRv.adapter = announcementRVAdapter
            binding.clubImminentRv.layoutManager = LinearLayoutManager(context)
        }
    }
}