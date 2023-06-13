package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityClubSignupFieldBinding
import okhttp3.MultipartBody

class ClubSignUpFieldActivity : AppCompatActivity() {
    lateinit var binding: ActivityClubSignupFieldBinding
    var field_list = ArrayList<String>()
    var btn_count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 값 가져오기
        var club_name = intent.getStringExtra("club_name")
        var club_id = intent.getStringExtra("club_id")
        var club_passwd = intent.getStringExtra("club_passwd")
        var region1 = intent.getStringExtra("region1")
        var region2 = intent.getStringExtra("region2")
        val profile : Uri = intent.getParcelableExtra("profile")!!

        Log.d("vales : ", "club_name: ${club_name}, club_id: ${club_id}, club_passwd: ${club_passwd}, field1: ${region1}, club_passwd: ${region2}")

        // 버튼 이벤트
        binding.btnArt.setOnClickListener {
            binding.btnArt.isSelected = !binding.btnArt.isSelected
            if(binding.btnArt.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("문화,예술,교양")
                }
            }
            else{
                btn_count--
                field_list.remove("문화,예술,교양")
            }


        }

        binding.btnVolunteer.setOnClickListener {
            binding.btnVolunteer.isSelected = !binding.btnVolunteer.isSelected
            if(binding.btnVolunteer.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("봉사,사회활동")
                }
            }
            else{
                btn_count--
                field_list.remove("봉사,사회활동")
            }
        }

        binding.btnResearch.setOnClickListener {

            binding.btnResearch.isSelected = !binding.btnResearch.isSelected
            if(binding.btnResearch.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("학술,교양")
                }
            }
            else{
                btn_count--
                field_list.remove("학술,교양")
            }
        }

        binding.btnEmployment.setOnClickListener {
            binding.btnEmployment.isSelected = !binding.btnEmployment.isSelected
            if(binding.btnEmployment.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("창업,취업")
                }
            }
            else{
                btn_count--
                field_list.remove("창업,취업")
            }
        }

        binding.btnLanguage.setOnClickListener {
            binding.btnLanguage.isSelected = !binding.btnLanguage.isSelected
            if(binding.btnLanguage.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("어학")
                }
            }
            else{
                btn_count--
                field_list.remove("어학")
            }
        }

        binding.btnSports.setOnClickListener {
            binding.btnSports.isSelected = !binding.btnSports.isSelected
            if(binding.btnSports.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("체육")
                }
            }
            else{
                btn_count--
                field_list.remove("체육")
            }
        }

        binding.btnFriend.setOnClickListener {
            binding.btnFriend.isSelected = !binding.btnFriend.isSelected
            if(binding.btnFriend.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("친목")
                }
            }
            else{
                btn_count--
                field_list.remove("친목")
            }
        }

        binding.btnEtc.setOnClickListener {
            binding.btnEtc.isSelected = !binding.btnEtc.isSelected
            if(binding.btnEtc.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "분야는 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnArt.isSelected = false
                }
                else {
                    btn_count++
                    field_list.add("기타")
                }
            }
            else{
                btn_count--
                field_list.remove("기타")
            }
        }

        binding.btnNext.setOnClickListener {
            if(field_list.size == 0){
                Toast.makeText(this, "분야를 선택해주세요(최소 1개, 최대 2개)", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, ClubSignUpEmailActivity::class.java)
                intent.putExtra("club_name", club_name)
                intent.putExtra("club_id", club_id)
                intent.putExtra("club_passwd", club_passwd)
                intent.putExtra("region1", region1)
                intent.putExtra("region2", region2)
                intent.putExtra("field1", field_list[0])
                if(field_list.size != 1)
                    intent.putExtra("field2", field_list[1])
                else
                    intent.putExtra("field2", "null")
                intent.putExtra("profile", profile)
                startActivity(intent)
                overridePendingTransition(0,0)
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}