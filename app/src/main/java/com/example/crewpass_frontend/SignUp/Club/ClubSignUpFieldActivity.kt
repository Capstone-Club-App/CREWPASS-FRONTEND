package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityClubSignupFieldBinding

class ClubSignUpFieldActivity : AppCompatActivity() {
    lateinit var binding: ActivityClubSignupFieldBinding
    var field_list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 값 가져오기
        intent.getStringArrayListExtra("region_list")

        // 버튼 이벤트
        binding.btnArt.setOnClickListener {
            binding.btnArt.isSelected = !binding.btnArt.isSelected
            if(binding.btnArt.isSelected){
                field_list.add("문화/예술/교양")
            }
            else{
                field_list.remove("문화/예술/교양")
            }
        }

        binding.btnVolunteer.setOnClickListener {
            binding.btnVolunteer.isSelected = !binding.btnVolunteer.isSelected
            if(binding.btnVolunteer.isSelected){
                field_list.add("봉사/사회활동")
            }
            else{
                field_list.remove("봉사/사회활동")
            }
        }

        binding.btnResearch.setOnClickListener {
            binding.btnResearch.isSelected = !binding.btnResearch.isSelected
            if(binding.btnResearch.isSelected){
                field_list.add("학술/교양")
            }
            else{
                field_list.remove("학술/교양")
            }
        }

        binding.btnEmployment.setOnClickListener {
            binding.btnEmployment.isSelected = !binding.btnEmployment.isSelected
            if(binding.btnEmployment.isSelected){
                field_list.add("창업/취업")
            }
            else{
                field_list.remove("창업/취업")
            }
        }

        binding.btnLanguage.setOnClickListener {
            binding.btnLanguage.isSelected = !binding.btnLanguage.isSelected
            if(binding.btnLanguage.isSelected){
                field_list.add("어학")
            }
            else{
                field_list.remove("어학")
            }
        }

        binding.btnSports.setOnClickListener {
            binding.btnSports.isSelected = !binding.btnSports.isSelected
            if(binding.btnSports.isSelected){
                field_list.add("체육")
            }
            else{
                field_list.remove("체육")
            }
        }

        binding.btnFriend.setOnClickListener {
            binding.btnFriend.isSelected = !binding.btnFriend.isSelected
            if(binding.btnFriend.isSelected){
                field_list.add("친목")
            }
            else{
                field_list.remove("친목")
            }
        }

        binding.btnEtc.setOnClickListener {
            binding.btnEtc.isSelected = !binding.btnEtc.isSelected
            if(binding.btnEtc.isSelected){
                field_list.add("기타")
            }
            else{
                field_list.remove("기타")
            }
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ClubSignUpEmailActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }
}