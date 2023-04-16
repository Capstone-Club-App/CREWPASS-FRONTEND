package com.example.crewpass_frontend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.AI.Personal.PersonalPrepareInterviewActivity
import com.example.crewpass_frontend.databinding.FragmentPersonAiBinding
import com.example.crewpass_frontend.databinding.ItemChatRoomBinding

class PersonAIFragment : Fragment() {
    lateinit var binding: FragmentPersonAiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonAiBinding.inflate(inflater,container,false)

        initActionBar()

        binding.btnQuestionPredict.setOnClickListener {
            val intent = Intent(activity, PersonalPrepareInterviewActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun initActionBar() {
        binding.innerPageTop.appbarBackBtn.visibility = View.INVISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameCenterTv.text = "AI"

    }
}