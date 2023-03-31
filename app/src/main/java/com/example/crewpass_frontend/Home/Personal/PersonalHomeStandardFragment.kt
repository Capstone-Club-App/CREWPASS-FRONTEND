package com.example.crewpass_frontend.Home.Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding

class PersonalHomeStandardFragment : Fragment() {
    lateinit var binding: FragmentPersonalHomeStandardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalHomeStandardBinding.inflate(inflater,container,false)


        return binding.root
    }
}