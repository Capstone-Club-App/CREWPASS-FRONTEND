package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.AI.Club.Interview.AIClubChooseRecruitmentActivity
import com.example.crewpass_frontend.AI.Club.Question.ChooseIdealActivity
import com.example.crewpass_frontend.databinding.FragmentClubAiBinding

class ClubAIFragment : Fragment() {
    lateinit var binding: FragmentClubAiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubAiBinding.inflate(inflater,container,false)

        initActionBar()

        binding.btnPrepareInterview.setOnClickListener {
            val intent = Intent(activity, AIClubChooseRecruitmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnQuestionRecommand.setOnClickListener {
            val intent = Intent(activity, ChooseIdealActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun initActionBar(){
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "AI"
    }

}