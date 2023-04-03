package com.example.crewpass_frontend.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.crewpass_frontend.Home.Personal.*
import com.example.crewpass_frontend.databinding.FragmentPersonalHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class PersonalHomeFragment:Fragment() {
    lateinit var binding: FragmentPersonalHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalHomeBinding.inflate(inflater, container, false)

        initViewPager()

        return binding.root
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ClubTabAdapter(this)
        viewPager2Adatper.addFragment(PersonalHomeStandardFragment())
        viewPager2Adatper.addFragment(FragmentCulture())
        viewPager2Adatper.addFragment(FragmentVolunteer())
        viewPager2Adatper.addFragment(FragmentResearch())
        viewPager2Adatper.addFragment(FragmentEmployment())
        viewPager2Adatper.addFragment(FragmentLanguage())
        viewPager2Adatper.addFragment(FragmentSports())
        viewPager2Adatper.addFragment(FragmentFriend())
        viewPager2Adatper.addFragment(FragmentEtc())



        //Adapter 연결
        binding.vpMainActivity.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.tabLayout, binding.vpMainActivity) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "전체"
                1 -> tab.text = "문화/예술/공연"
                2 -> tab.text = "봉사/사회활동"
                3 -> tab.text = "학술/교양"
                4 -> tab.text = "창업/취업"
                5 -> tab.text = "어학"
                6 -> tab.text = "체육"
                7 -> tab.text = "친목"
                8 -> tab.text = "기타"
            }
        }.attach()
    }
}