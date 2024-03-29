package com.example.crewpass_frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crewpass_frontend.Home.ClubHomeFragment
import com.example.crewpass_frontend.Home.PersonalHomeFragment
import com.example.crewpass_frontend.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = intent.getStringExtra("Key")
        Log.d("Key : ", key.toString())

        if (key.equals("Club"))
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_main_container, ClubHomeFragment())
        else if (key.equals("Personal"))
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_main_container, PersonalHomeFragment())



        binding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        if (key.equals("Club"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, ClubHomeFragment())
                                .commitAllowingStateLoss()
                        else if (key.equals("Personal"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, PersonalHomeFragment())
                                .commitAllowingStateLoss()

                    }

                    R.id.menu_chat -> {
                        val chatFragment = ChatFragment()
                        val bundle = Bundle()
                        bundle.putString("Key", key)
                        chatFragment.arguments = bundle
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main_container, chatFragment)
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_ai -> {
                        if (key.equals("Club"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, ClubAIFragment())
                                .commitAllowingStateLoss()
                        else if (key.equals("Personal"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, PersonAIFragment())
                                .commitAllowingStateLoss()
                    }

                    R.id.menu_mypage -> {
                        if (key.equals("Club"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, ClubMyPageFragment())
                                .commitAllowingStateLoss()
                        else if (key.equals("Personal"))
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_main_container, PersonalMyPageFragment())
                                .commitAllowingStateLoss()
                    }
                }
                true
            }
            //처음 실행시 자동으로 home 화면을 가르키게 됨.
            selectedItemId = R.id.menu_home

        }
    }
}