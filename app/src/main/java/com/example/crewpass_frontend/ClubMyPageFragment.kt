package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.MyPage.Club.ClubEditInfoActivity
import com.example.crewpass_frontend.MyPage.Club.ClubEditRecruitmentActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalEditInfoActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalScrapActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalSubmitPrevActivity
import com.example.crewpass_frontend.databinding.FragmentClubMypageBinding

class ClubMyPageFragment : Fragment(){
    lateinit var binding : FragmentClubMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubMypageBinding.inflate(inflater,container,false)

        initActionBar()

        binding.btnEditInfo.setOnClickListener {
            val intent = Intent(activity, ClubEditInfoActivity::class.java)
            startActivity(intent)
        }

        binding.btnEditAnnouncement.setOnClickListener {
            val intent = Intent(activity, ClubEditRecruitmentActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "마이페이지"

    }
}