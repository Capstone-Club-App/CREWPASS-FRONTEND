package com.example.crewpass_frontend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.databinding.FragmentClubHomeBinding

class ClubHomeFragment:Fragment() {
    lateinit var binding: FragmentClubHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
}