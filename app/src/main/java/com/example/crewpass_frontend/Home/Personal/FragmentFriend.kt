package com.example.crewpass_frontend.Home.Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.databinding.FragmentFriendBinding
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding

class FragmentFriend : Fragment() {
    lateinit var binding: FragmentFriendBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater,container,false)


        return binding.root
    }
}