package com.example.crewpass_frontend.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.Club.ClubRecruitmentRVAdapter
import com.example.crewpass_frontend.Home.Club.List.ClubRecruitmentListActivity
import com.example.crewpass_frontend.Home.Club.Recent.ClubRecentListActivity

import com.example.crewpass_frontend.Home.Club.WriteRecruitmentActivity
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ClubHomeFragment:Fragment() {
    lateinit var binding: FragmentClubHomeBinding

    lateinit var announcementRVAdapter: ClubRecruitmentRVAdapter

    var club_recent_list = ArrayList<Recruitment>()
    var club_my_list = ArrayList<Recruitment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubHomeBinding.inflate(inflater,container,false)

        initRecyclerView()

        binding.btnWriteRecruitment.setOnClickListener {
            val intent = Intent(context, WriteRecruitmentActivity::class.java)
            startActivity(intent)

        }

        binding.btnMyRecruitment.setOnClickListener {
            val intent = Intent(context, ClubRecruitmentListActivity::class.java)
            startActivity(intent)
        }

        binding.btnRecentRecruitment.setOnClickListener {
            val intent = Intent(context, ClubRecentListActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun initRecyclerView(){
        club_my_list.apply {
            add(Recruitment("내 동아리1", "제목1", "내용1"))
            add(Recruitment("내 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubRecruitmentRVAdapter(club_my_list)
            binding.myRecruitmentRv.adapter = announcementRVAdapter
            binding.myRecruitmentRv.layoutManager = LinearLayoutManager(context)
        }

        club_recent_list.apply {
            add(Recruitment("최신 동아리1","제목1", "내용1"))
            add(Recruitment("최신 동아리2", "제목2", "내용2"))

            announcementRVAdapter = ClubRecruitmentRVAdapter(club_recent_list)
            binding.clubRecentRv.adapter = announcementRVAdapter
            binding.clubRecentRv.layoutManager = LinearLayoutManager(context)
        }
    }
}