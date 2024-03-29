package com.example.crewpass_frontend

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Login.LoginActivity
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.MyPage.Personal.LogoutDialog
import com.example.crewpass_frontend.MyPage.Personal.PersonalEditInfoActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalScrapActivity
import com.example.crewpass_frontend.MyPage.Personal.PersonalSubmitPrevActivity
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalData
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalService
import com.example.crewpass_frontend.databinding.FragmentPersonalMypageBinding

class PersonalMyPageFragment : Fragment(), PersonalGetResult, LogoutDialog.LogoutDialogInterface{
    lateinit var binding : FragmentPersonalMypageBinding

    var userLoginId = ""
    var userPw = ""
    var userName = ""
    var userEmail = ""
    var job = ""
    var school = ""
    var profile = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalMypageBinding.inflate(inflater,container,false)

        initActionBar()
        getPersonal()

        binding.btnEditInfo.setOnClickListener {
//            val intent = Intent(activity, PersonalEditInfoActivity::class.java)
//            intent.putExtra("userLoginId", userLoginId)
//            intent.putExtra("userPw", userPw)
//            intent.putExtra("userName", userName)
//            intent.putExtra("userEmail", userEmail)
//            intent.putExtra("job", job)
//            intent.putExtra("school", school)
//            intent.putExtra("profile", profile)
//            startActivity(intent)
            Toast.makeText(requireContext(), "아직 미완성인 기능으로, 추후에 개발예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.btnScrap.setOnClickListener {
            val intent = Intent(activity, PersonalScrapActivity::class.java)
            startActivity(intent)
        }

        binding.btnSubmitPrev.setOnClickListener {
            val intent = Intent(activity, PersonalSubmitPrevActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            val dlg = LogoutDialog(requireContext(), this)
            dlg.start()
        }

        return binding.root
    }

    // 회원 정보 가져오기
    fun getPersonal(){
        val personalService = PersonalService()
        personalService.setPersonalGetResult(this)
        personalService.getPersonal(logined_id.toString())
    }

    override fun personalGetSuccess(code: Int, data: PersonalData) {
        Log.d("회원정보 가져오기 성공", "")
        binding.txtName.text = data.userName
        userLoginId = data.userLoginId
        userPw = data.userPw
        userName = data.userName
        userEmail = data.userEmail
        job = data.job
        school = data.school
        profile = data.userProfile

        Glide.with(this).load(data.userProfile)
            .circleCrop()
            .into(binding.imgProfile)
    }

    override fun personalGetFailure(code: Int) {
        Log.d("회원정보 가져오기 실패", "")
    }


    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "마이페이지"

    }

    @SuppressLint("CommitPrefEdits")
    override fun onYesButtonClicked() {
        val pref = requireActivity().getSharedPreferences("loginId", AppCompatActivity.MODE_PRIVATE)
        val edit = pref.edit() // 수정모드
        edit.clear()
        edit.commit()
        activity?.let {
            val intent = Intent(context, LoginActivity::class.java)
            Toast.makeText(context, "로그아웃 되었습니다. 로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show()
            activity?.finish()
            startActivity(intent)
        }
    }
}