package com.example.crewpass_frontend.SignUp.Club

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.databinding.ActivityClubSignupRegionBinding
import okhttp3.MultipartBody

class ClubSignUpRegionActivity : AppCompatActivity() {
    lateinit var binding : ActivityClubSignupRegionBinding
    var region_list = ArrayList<String>()
    var btn_count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var club_name = intent.getStringExtra("club_name")
        var club_id = intent.getStringExtra("club_id")
        var club_passwd = intent.getStringExtra("club_passwd")
        val profile : Uri = intent.getParcelableExtra("profile")!!


        Log.d("vales : ", "club_name: ${club_name}, club_id: ${club_id}, club_passwd: ${club_passwd}, profile: ${profile}")

        binding.btnAllRegion.setOnClickListener {
            binding.btnAllRegion.isSelected = !binding.btnAllRegion.isSelected

            if(binding.btnAllRegion.isSelected){
                region_list.add("전체")
                if(binding.btnSeoul.isSelected || binding.btnGyeongi.isSelected || binding.btnGangwon.isSelected
                    || binding.btnChungcheonNam.isSelected || binding.btnChungcheonBook.isSelected
                    || binding.btnJeollaNam.isSelected || binding.btnJeollaBook.isSelected
                    || binding.btnGyeonsangNam.isSelected || binding.btnGyeonsangBook.isSelected || binding.btnJeju.isSelected){
                    btn_count = 1
                    binding.btnSeoul.isSelected = false
                    binding.btnGyeongi.isSelected = false
                    binding.btnGangwon.isSelected = false
                    binding.btnChungcheonNam.isSelected = false
                    binding.btnChungcheonBook.isSelected = false
                    binding.btnJeollaNam.isSelected = false
                    binding.btnJeollaBook.isSelected = false
                    binding.btnGyeonsangNam.isSelected = false
                    binding.btnGyeonsangBook.isSelected = false
                    binding.btnJeju.isSelected = false

                    region_list.remove("서울")
                    region_list.remove("경기")
                    region_list.remove("강원도")
                    region_list.remove("충청남도")
                    region_list.remove("충청북도")
                    region_list.remove("전라남도")
                    region_list.remove("전라북도")
                    region_list.remove("경상남도")
                    region_list.remove("경상북도")
                    region_list.remove("제주도")
                }
            }
            else{
                btn_count--
                region_list.remove("전체")
            }


        }

        binding.btnSeoul.setOnClickListener {
            binding.btnSeoul.isSelected = !binding.btnSeoul.isSelected
            if(binding.btnSeoul.isSelected) {
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnSeoul.isSelected = false
                }
                else{
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("서울")
                }
            }
            else{
                btn_count--
                region_list.remove("서울")
            }
        }

        binding.btnGyeongi.setOnClickListener {
            binding.btnGyeongi.isSelected = !binding.btnGyeongi.isSelected
            if(binding.btnGyeongi.isSelected) {
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeongi.isSelected = false
                } else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("경기도")
                }
            }
            else{
                btn_count--
                region_list.remove("경기도")
            }
        }

        binding.btnGangwon.setOnClickListener {
            binding.btnGangwon.isSelected = !binding.btnGangwon.isSelected
            if(binding.btnGangwon.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGangwon.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("강원도")
                }
            }
            else{
                btn_count--
                region_list.remove("강원도")
            }
        }

        binding.btnChungcheonNam.setOnClickListener {
            binding.btnChungcheonNam.isSelected = !binding.btnChungcheonNam.isSelected
            if(binding.btnChungcheonNam.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnChungcheonNam.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("충청남도")
                }
            }
            else{
                btn_count--
                region_list.remove("충청남도")
            }
        }

        binding.btnChungcheonBook.setOnClickListener {
            binding.btnChungcheonBook.isSelected = !binding.btnChungcheonBook.isSelected
            if(binding.btnChungcheonBook.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnChungcheonBook.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("충청북도")
                }
            }
            else{
                btn_count--
                region_list.remove("충청북도")
            }
        }

        binding.btnJeollaNam.setOnClickListener {
            binding.btnJeollaNam.isSelected = !binding.btnJeollaNam.isSelected
            if(binding.btnJeollaNam.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeollaNam.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("전라남도")
                }
            }
            else{
                btn_count--
                region_list.remove("전라남도")
            }
        }

        binding.btnJeollaBook.setOnClickListener {
            binding.btnJeollaBook.isSelected = !binding.btnJeollaBook.isSelected
            if(binding.btnJeollaBook.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeollaBook.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("전라북도")
                }
            }
            else{
                btn_count--
                region_list.remove("전라북도")
            }
        }

        binding.btnGyeonsangNam.setOnClickListener {
            binding.btnGyeonsangNam.isSelected = !binding.btnGyeonsangNam.isSelected
            if(binding.btnGyeonsangNam.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeonsangNam.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("경상남도")
                }
            }
            else{
                btn_count--
                region_list.remove("경상남도")
            }
        }

        binding.btnGyeonsangBook.setOnClickListener {
            binding.btnGyeonsangBook.isSelected = !binding.btnGyeonsangBook.isSelected
            if(binding.btnGyeonsangBook.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnGyeonsangBook.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("경상북도")
                }
            }
            else{
                btn_count--
                region_list.remove("경상북도")
            }
        }

        binding.btnJeju.setOnClickListener {
            binding.btnJeju.isSelected = !binding.btnJeju.isSelected
            if(binding.btnJeju.isSelected){
                if (btn_count == 2) {
                    Toast.makeText(this, "지역은 최대 2개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    binding.btnJeju.isSelected = false
                }
                else {
                    btn_count++
                    if (binding.btnAllRegion.isSelected) {
                        binding.btnAllRegion.isSelected = false
                        region_list.remove("전체")
                        btn_count--
                    }
                    region_list.add("제주도")
                }
            }
            else{
                btn_count--
                region_list.remove("제주도")
            }
        }


        binding.btnNext.setOnClickListener {
            for(i in 0..region_list.size-1){
                Log.d("region_list : ", i.toString() + ": " +region_list[i] )
            }
            val intent = Intent(this, ClubSignUpFieldActivity::class.java)
            intent.putExtra("club_name", club_name)
            intent.putExtra("club_id", club_id)
            intent.putExtra("club_passwd", club_passwd)
            intent.putExtra("region1", region_list[0])
            if(region_list.size != 1)
                intent.putExtra("region2", region_list[1])
            else
                intent.putExtra("region2", "null")
            intent.putExtra("profile", profile)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }
}