package com.example.crewpass_frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crewpass_frontend.Home.ClubHomeFragment
import com.example.crewpass_frontend.Home.PersonalHomeFragment
import com.example.crewpass_frontend.SignUp.MyPageFragment
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
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main_container, ChatFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_ai -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main_container, AIFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main_container, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            //처음 실행시 자동으로 home 화면을 가르키게 됨.
            selectedItemId = R.id.menu_home


        }

//        fun initClub() {
//            // 동아리인 경우
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_main_container, ClubHomeFragment())
//
//            binding.navBottom.run {
//                setOnItemSelectedListener {
//                    when (it.itemId) {
//                        R.id.menu_home -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.container.id, ClubHomeFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_chat -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.container.id, ChatFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_ai -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.container.id, AIFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_mypage -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.container.id, MyPageFragment())
//                                .commitAllowingStateLoss()
//                        }
//                    }
//                    true
//                }
//                //처음 실행시 자동으로 home 화면을 가르키게 됨.
//                selectedItemId = R.id.menu_home
//            }
//        }

//        fun initPersonal() {
//            // 일반회원인 경우
//            supportFragmentManager.beginTransaction()
//                .replace(binding.containerFragment.id, PersonalHomeFragment())
//
//            binding.navBottom.run {
//                setOnItemSelectedListener {
//                    when (it.itemId) {
//                        R.id.menu_home -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.containerFragment.id, PersonalHomeFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_chat -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.containerFragment.id, ChatFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_ai -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.containerFragment.id, AIFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                        R.id.menu_mypage -> {
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.containerFragment.id, MyPageFragment())
//                                .commitAllowingStateLoss()
//                        }
//                    }
//                    true
//                }
//                //처음 실행시 자동으로 home 화면을 가르키게 됨.
//                selectedItemId = R.id.menu_home
//            }
//        }
    }
}