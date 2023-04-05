package com.example.crewpass_frontend.SignUp.Personal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityPersonalSignupChooseBinding

class PersonalSignUpChooseActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalSignupChooseBinding
    var gubun = ""
    var mid_isSelected = false
    var high_isSelected = false
    var col_isSelected = false
    var adult_isSelected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSignupChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnMid.setOnClickListener {
            mid_isSelected = !mid_isSelected
            if(mid_isSelected){
                binding.btnMid.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(high_isSelected || col_isSelected || adult_isSelected){
                    high_isSelected = false
                    binding.btnHigh.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    col_isSelected = false
                    binding.btnCollege.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    adult_isSelected = false
                    binding.btnAdult.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                gubun = "midd_list"
            }

        }

        binding.btnHigh.setOnClickListener {
            high_isSelected = !high_isSelected
            if(high_isSelected){
                binding.btnHigh.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(mid_isSelected || col_isSelected || adult_isSelected){
                    mid_isSelected = false
                    binding.btnMid.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    col_isSelected = false
                    binding.btnCollege.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    adult_isSelected = false
                    binding.btnAdult.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                gubun = "high_list"
            }
        }

        binding.btnCollege.setOnClickListener {
            col_isSelected = !col_isSelected
            if(col_isSelected){
                binding.btnCollege.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(mid_isSelected || high_isSelected || adult_isSelected){
                    mid_isSelected = false
                    binding.btnMid.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    high_isSelected = false
                    binding.btnHigh.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    adult_isSelected = false
                    binding.btnAdult.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                gubun = "univ_list"
            }
        }

        binding.btnAdult.setOnClickListener {
            adult_isSelected = !adult_isSelected
            if(adult_isSelected){
                binding.btnAdult.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(mid_isSelected || high_isSelected || col_isSelected){
                    mid_isSelected = false
                    binding.btnMid.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    high_isSelected = false
                    binding.btnHigh.setBackgroundColor(Color.parseColor("#F4F4F4"))
                    col_isSelected = false
                    binding.btnCollege.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
            }
        }

        binding.btnNext.setOnClickListener {
            if (adult_isSelected){ // 성인 선택
                val intent = Intent(this, PersonalSignUpDoneActivity::class.java)
                startActivity(intent)
                overridePendingTransition(0,0)
            }
            else{
                val intent = Intent(this, PersonalSignUpSchoolActivity::class.java)
                intent.putExtra("gubun", gubun)

                startActivity(intent)
                overridePendingTransition(0,0)
            }
        }


    }
}