package com.example.crewpass_frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crewpass_frontend.SignUp.MyPageFragment
import com.example.crewpass_frontend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val key = intent.getStringExtra("Key")
        Log.d("Key : ", key.toString())

        if(key.equals("Club"))
            initClub()
        else if(key.equals("Personal"))
            initPersonal()


    }

    fun initClub(){
        // 동아리인 경우
        supportFragmentManager.beginTransaction().replace(binding.containerFragment.id, ClubHomeFragment())

        binding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId){
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, ClubHomeFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_chat -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, ChatFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_ai -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, AIFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            //처음 실행시 자동으로 home 화면을 가르키게 됨.
            selectedItemId = R.id.menu_home
        }
    }

    fun initPersonal(){
        // 일반회원인 경우
        supportFragmentManager.beginTransaction().replace(binding.containerFragment.id, PersonalHomeFragment())

        binding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId){
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, PersonalHomeFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_chat -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, ChatFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_ai -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, AIFragment())
                            .commitAllowingStateLoss()
                    }

                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MyPageFragment())
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