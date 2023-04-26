package com.example.crewpass_frontend.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentRVAdapter
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ClubHomeFragment:Fragment() {
    lateinit var binding: FragmentClubHomeBinding

    lateinit var announcementRVAdapter: ClubRecruitmentRVAdapter

    var club_recent_list = ArrayList<Recruitment>()
    var club_imminent_list = ArrayList<Recruitment>()

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
            add(Recruitment("최신 동아리1", "제목1", "내용1"))
            add(Recruitment("최신 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubRecruitmentRVAdapter(club_recent_list)
            binding.clubRecentRv.adapter = announcementRVAdapter
            binding.clubRecentRv.layoutManager = LinearLayoutManager(context)
        }

        club_imminent_list.apply {
            add(Recruitment("마감임박 동아리1","제목1", "내용1"))
            add(Recruitment("마감임박 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubRecruitmentRVAdapter(club_imminent_list)
            binding.clubImminentRv.adapter = announcementRVAdapter
            binding.clubImminentRv.layoutManager = LinearLayoutManager(context)
        }
    }
}