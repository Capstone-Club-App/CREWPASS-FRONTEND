package com.example.crewpass_frontend.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeBinding

class PersonalHomeFragment:Fragment() {
    lateinit var binding: FragmentPersonalHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
}