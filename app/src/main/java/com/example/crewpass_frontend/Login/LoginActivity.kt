package com.example.crewpass_frontend.Login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.IDPW_Find.IDPWFindActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.SignUp.Club.ClubSignUpActivity
import com.example.crewpass_frontend.SignUp.Personal.PersonalSignUpActivity
import com.example.crewpass_frontend.SignUp.SignUpDialog
import com.example.crewpass_frontend.databinding.ActivityLoginBinding

class LoginActivity:AppCompatActivity(), MyCustomDialogInterface, SignUpDialog.SignUpDialogInterface {
    lateinit var binding: ActivityLoginBinding
    var string = ""
    var club_clicked = false
    var personal_clicked = false
    var choice_type : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.personalBtn.setOnClickListener {
            if(personal_clicked){
                personal_clicked = !personal_clicked // 클릭 취소 상태로 변경해주기
                binding.personalBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            }
            else{ // false
                personal_clicked = !personal_clicked // 클릭 상태로 변경해주기
                binding.personalBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(club_clicked){
                    club_clicked = !club_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.clubBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                string = "Personal"
            }
            println(personal_clicked.toString())
        }

        binding.clubBtn.setOnClickListener {
            if(club_clicked){ // 이미 누른 상태에서 다시 눌렀을 때
                club_clicked = !club_clicked // 클릭 취소 상태로 변경해주기
                binding.clubBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            }
            else{
                club_clicked = !club_clicked // 클릭 상태로 변경해주기
                binding.clubBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if(personal_clicked){
                    personal_clicked = !personal_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.personalBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                string = "Club"
            }
            println(club_clicked.toString())
        }


        // 로그인 버튼
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Key", string)
            startActivity(intent)
        }


        // 회원가입 클릭
        binding.textSignUp.setOnClickListener {
            val signUpDialog = SignUpDialog(this, this)
            signUpDialog.start()
        }

        binding.textFind.setOnClickListener{
            val intent = Intent(this, IDPWFindActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onbtnGotoMainClicked() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Key", string)
        startActivity(intent)
    }

    override fun onPersonalButtonClicked() {
        val intent = Intent(this, PersonalSignUpActivity::class.java)
        startActivity(intent)
    }

    override fun onClubButtonClicked() {
        val intent = Intent(this, ClubSignUpActivity::class.java)
        startActivity(intent)
    }
}