package com.example.crewpass_frontend.Home.Personal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Recruitment
import com.example.crewpass_frontend.Home.HomeImminentRVAdapter
import com.example.crewpass_frontend.Home.HomeRecentRVAdapter
import com.example.crewpass_frontend.Home.Personal.List.RecruitmentDetailActivity
import com.example.crewpass_frontend.Home.Personal.List.PersonalRecruitmentListActivity
import com.example.crewpass_frontend.databinding.FragmentCultureBinding

class FragmentCulture : Fragment() {
    lateinit var binding: FragmentCultureBinding

    lateinit var homeRecentRVAdapter: HomeRecentRVAdapter
    lateinit var homeImminentRVAdapter: HomeImminentRVAdapter

    var recent_list = ArrayList<Recruitment>()
    var imminent_list = ArrayList<Recruitment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCultureBinding.inflate(inflater,container,false)


        binding.btnCultureRecent.setOnClickListener {
            val intent = Intent(activity, PersonalRecruitmentListActivity::class.java)
            intent.putExtra("list_state", "recent")
            startActivity(intent)
        }

        binding.btnCultureImminent.setOnClickListener {
            val intent = Intent(activity, PersonalRecruitmentListActivity::class.java)
            intent.putExtra("list_state", "imminent")
            startActivity(intent)
        }

        initRecyclerView()

        return binding.root
    }


    fun initRecyclerView(){
        recent_list.apply {
            add(Recruitment("문화 최신 동아리1", "제목1", "내용1"))
            add(Recruitment("문화 최신 동아리2", "제목2", "내용2"))

            homeRecentRVAdapter = HomeRecentRVAdapter(recent_list)
            binding.cultureRecentRv.adapter = homeRecentRVAdapter
            binding.cultureRecentRv.layoutManager = LinearLayoutManager(context)
            homeRecentRVAdapter.setItemClickListener(object :
                HomeRecentRVAdapter.OnItemClickListener {
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }

        imminent_list.apply {
            add(Recruitment("문화 마감임박 동아리1", "제목1", "내용1"))
            add(Recruitment("문화 마감임박 동아리2", "제목2", "내용2"))

            homeImminentRVAdapter = HomeImminentRVAdapter(recent_list)
            binding.cultureImminentRv.adapter = homeImminentRVAdapter
            binding.cultureImminentRv.layoutManager = LinearLayoutManager(context)
            homeImminentRVAdapter.setItemClickListener(object :
                HomeImminentRVAdapter.OnItemClickListener {
                override fun onItemClick(recruitment: Recruitment) {
                    val intent = Intent(context, RecruitmentDetailActivity::class.java)
                    intent.putExtra("scrap", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }
}