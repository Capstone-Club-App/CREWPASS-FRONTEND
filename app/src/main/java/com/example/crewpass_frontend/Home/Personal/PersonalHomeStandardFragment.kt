package com.example.crewpass_frontend.Home.Personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.crewpass_frontend.Home.Personal.List.AnnouncementListActivity
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeStandardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PersonalHomeStandardFragment : Fragment() {
    lateinit var binding: FragmentPersonalHomeStandardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentPersonalHomeStandardBinding.inflate(inflater,container,false)

        binding.btnStandardRecent.setOnClickListener {
            val intent = Intent(activity, AnnouncementListActivity::class.java)
            intent.putExtra("list_state", "recent")
            startActivity(intent)
        }

        binding.btnStandardImminent.setOnClickListener {
            val intent = Intent(activity, AnnouncementListActivity::class.java)
            intent.putExtra("list_state", "imminent")
            startActivity(intent)
        }

        return binding.root
    }


}