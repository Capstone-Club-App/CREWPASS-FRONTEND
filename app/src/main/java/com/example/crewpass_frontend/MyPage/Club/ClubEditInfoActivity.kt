package com.example.crewpass_frontend.MyPage.Club

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityClubEditInfoBinding

class ClubEditInfoActivity:AppCompatActivity() {
    lateinit var binding: ActivityClubEditInfoBinding
    var region_list = ArrayList<String>()
    var field_list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRegion()
        setField()
    }

    fun setRegion(){
        binding.btnAllRegion.setOnClickListener {
            binding.btnAllRegion.isSelected = !binding.btnAllRegion.isSelected

            if(binding.btnAllRegion.isSelected){
                region_list.add("전체")
                if(binding.btnSeoul.isSelected || binding.btnGyeongi.isSelected || binding.btnGangwon.isSelected
                    || binding.btnChungcheonNam.isSelected || binding.btnChungcheonBook.isSelected
                    || binding.btnJeollaNam.isSelected || binding.btnJeollaBook.isSelected
                    || binding.btnGyeonsangNam.isSelected || binding.btnGyeonsangBook.isSelected || binding.btnJeju.isSelected){
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
                region_list.remove("전체")
            }


        }

        binding.btnSeoul.setOnClickListener {
            binding.btnSeoul.isSelected = !binding.btnSeoul.isSelected
            if(binding.btnSeoul.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("서울")
            }
            else{
                region_list.remove("서울")
            }
        }

        binding.btnGyeongi.setOnClickListener {
            binding.btnGyeongi.isSelected = !binding.btnGyeongi.isSelected
            if(binding.btnGyeongi.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("경기도")
            }
            else{
                region_list.remove("경기도")
            }
        }

        binding.btnGangwon.setOnClickListener {
            binding.btnGangwon.isSelected = !binding.btnGangwon.isSelected
            if(binding.btnGangwon.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("강원도")
            }
            else{
                region_list.remove("강원도")
            }
        }

        binding.btnChungcheonNam.setOnClickListener {
            binding.btnChungcheonNam.isSelected = !binding.btnChungcheonNam.isSelected
            if(binding.btnChungcheonNam.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("충청남도")
            }
            else{
                region_list.remove("충청남도")
            }
        }

        binding.btnChungcheonBook.setOnClickListener {
            binding.btnChungcheonBook.isSelected = !binding.btnChungcheonBook.isSelected
            if(binding.btnChungcheonBook.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("충청북도")
            }
            else{
                region_list.remove("충청북도")
            }
        }

        binding.btnJeollaNam.setOnClickListener {
            binding.btnJeollaNam.isSelected = !binding.btnJeollaNam.isSelected
            if(binding.btnJeollaNam.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("전라남도")
            }
            else{
                region_list.remove("전라남도")
            }
        }

        binding.btnJeollaBook.setOnClickListener {
            binding.btnJeollaBook.isSelected = !binding.btnJeollaBook.isSelected
            if(binding.btnJeollaBook.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("전라북도")
            }
            else{
                region_list.remove("전라북도")
            }
        }

        binding.btnGyeonsangNam.setOnClickListener {
            binding.btnGyeonsangNam.isSelected = !binding.btnGyeonsangNam.isSelected
            if(binding.btnGyeonsangNam.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("경상남도")
            }
            else{
                region_list.remove("경상남도")
            }
        }

        binding.btnGyeonsangBook.setOnClickListener {
            binding.btnGyeonsangBook.isSelected = !binding.btnGyeonsangBook.isSelected
            if(binding.btnGyeonsangBook.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("경상북도")
            }
            else{
                region_list.remove("경상북도")
            }
        }

        binding.btnJeju.setOnClickListener {
            binding.btnJeju.isSelected = !binding.btnJeju.isSelected
            if(binding.btnJeju.isSelected){
                if(binding.btnAllRegion.isSelected)
                    region_list.remove("전체")
                region_list.add("제주도")
            }
            else{
                region_list.remove("제주도")
            }
        }
    }

    fun setField(){
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
    }
}