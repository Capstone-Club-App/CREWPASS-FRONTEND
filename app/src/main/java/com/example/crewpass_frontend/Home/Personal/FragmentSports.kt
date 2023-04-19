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
import com.example.crewpass_frontend.Home.Personal.List.PersonalAnnouncementListActivity
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding
import com.example.crewpass_frontend.databinding.FragmentSportsBinding

class FragmentSports : Fragment() {
    lateinit var binding: FragmentSportsBinding

    lateinit var homeRecentRVAdapter: HomeRecentRVAdapter
    lateinit var homeImminentRVAdapter: HomeImminentRVAdapter

    var recent_list = ArrayList<Announcement>()
    var imminent_list = ArrayList<Announcement>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportsBinding.inflate(inflater,container,false)

        binding.btnSportsRecent.setOnClickListener {
            val intent = Intent(activity, PersonalAnnouncementListActivity::class.java)
            intent.putExtra("list_state", "recent")
            startActivity(intent)
        }

        binding.btnSportsImminent.setOnClickListener {
            val intent = Intent(activity, PersonalAnnouncementListActivity::class.java)
            intent.putExtra("list_state", "imminent")
            startActivity(intent)
        }

        initRecyclerView()

        return binding.root
    }


    fun initRecyclerView(){
        recent_list.apply {
            add(Announcement("최신 동아리1", "제목1", "내용1"))
            add(Announcement("최신 동아리2", "제목2", "내용2"))

            homeRecentRVAdapter = HomeRecentRVAdapter(recent_list)
            binding.sportsRecentRv.adapter = homeRecentRVAdapter
            binding.sportsRecentRv.layoutManager = LinearLayoutManager(context)
        }

        imminent_list.apply {
            add(Announcement("최신 동아리1", "제목1", "내용1"))
            add(Announcement("최신 동아리2", "제목2", "내용2"))

            homeImminentRVAdapter = HomeImminentRVAdapter(recent_list)
            binding.sportsImminentRv.adapter = homeImminentRVAdapter
            binding.sportsImminentRv.layoutManager = LinearLayoutManager(context)
        }
    }
}