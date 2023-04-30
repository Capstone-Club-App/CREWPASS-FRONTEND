package com.example.crewpass_frontend.AI.Club.Question

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityChooseIdealBinding

class ChooseIdealActivity:AppCompatActivity() {
    lateinit var binding : ActivityChooseIdealBinding
    var ideal_list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseIdealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        // 버튼 이벤트
        binding.btnSincerity.setOnClickListener {
            binding.btnSincerity.isSelected = !binding.btnSincerity.isSelected
            if(binding.btnSincerity.isSelected){
                ideal_list.add("성실")
            }
            else{
                ideal_list.remove("성실")
            }
        }

        binding.btnLively.setOnClickListener {
            binding.btnLively.isSelected = !binding.btnLively.isSelected
            if(binding.btnLively.isSelected){
                ideal_list.add("활기참")
            }
            else{
                ideal_list.remove("활기참")
            }
        }

        binding.btnBright.setOnClickListener {
            binding.btnBright.isSelected = !binding.btnBright.isSelected
            if(binding.btnBright.isSelected){
                ideal_list.add("밝음")
            }
            else{
                ideal_list.remove("밝음")
            }
        }

        binding.btnManner.setOnClickListener {
            binding.btnManner.isSelected = !binding.btnManner.isSelected
            if(binding.btnManner.isSelected){
                ideal_list.add("인사성이 바름")
            }
            else{
                ideal_list.remove("인사성이 바름")
            }
        }

        binding.btnKind.setOnClickListener {
            binding.btnKind.isSelected = !binding.btnKind.isSelected
            if(binding.btnKind.isSelected){
                ideal_list.add("친절함")
            }
            else{
                ideal_list.remove("친절함")
            }
        }

        binding.btnPatience.setOnClickListener {
            binding.btnPatience.isSelected = !binding.btnPatience.isSelected
            if(binding.btnPatience.isSelected){
                ideal_list.add("끈기")
            }
            else{
                ideal_list.remove("끈기")
            }
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, AnalyzeApplicationActivity::class.java)
            startActivity(intent)
        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 질문 AI 추천"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}