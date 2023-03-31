package com.example.crewpass_frontend.Home.Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding
import com.example.crewpass_frontend.databinding.FragmentResearchBinding

class FragmentResearch : Fragment() {
    lateinit var binding: FragmentResearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResearchBinding.inflate(inflater,container,false)


        return binding.root
    }
}