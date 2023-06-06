package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.MyPage.Club.ClubEditInfoActivity
import com.example.crewpass_frontend.MyPage.Club.ClubEditRecruitmentActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalEditInfoActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalScrapActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalSubmitPrevActivity
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubData
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubGetResult
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubService
import com.example.crewpass_frontend.databinding.FragmentClubMypageBinding

class ClubMyPageFragment : Fragment(), ClubGetResult{
    lateinit var binding : FragmentClubMypageBinding
    var crewLoginId = ""
    var crewPw = ""
    var crewName = ""
    var region1 = ""
    var region2 = ""
    var field1 = ""
    var field2 = ""
    var crewMasterEmail = ""
    var crewSubEmail = ""
    var profile = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubMypageBinding.inflate(inflater,container,false)

        initActionBar()

        getClub()

        binding.btnEditInfo.setOnClickListener {
            val intent = Intent(activity, ClubEditInfoActivity::class.java)
            intent.putExtra("crewLoginId", crewLoginId)
            intent.putExtra("crewPw", crewPw)
            intent.putExtra("crewName", crewName)
            intent.putExtra("region1", region1)
            intent.putExtra("region2", region2)
            intent.putExtra("field1", field1)
            intent.putExtra("field2", field2)
            intent.putExtra("crewMasterEmail", crewMasterEmail)
            intent.putExtra("crewSubEmail", crewSubEmail)
            intent.putExtra("profile", profile)
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

    // 동아리 정보 불러오기
    fun getClub(){
        val clubService = ClubService()
        clubService.setClubGetResult(this)
        clubService.getClub(logined_id)
    }

    override fun clubGetSuccess(code: Int, data: ClubData) {
        Log.d("동아리정보 가져오기 성공", "")
        binding.txtName.text = data.crewName

        crewLoginId = data.crewLoginId
        crewPw = data.crewPw
        crewName = data.crewName
        region1 = data.region1
        region2 = data.region2
        field1 = data.field1
        field1 = data.field2
        crewMasterEmail = data.crewMasterEmail
        crewSubEmail = data.crewSubEmail
        profile = data.crew_profile

        Glide.with(this).load(data.crew_profile)
            .circleCrop()
            .into(binding.imgProfile)
    }

    override fun clubGetFailure(code: Int) {
        Log.d("동아리정보 가져오기 실패", "")
    }
}