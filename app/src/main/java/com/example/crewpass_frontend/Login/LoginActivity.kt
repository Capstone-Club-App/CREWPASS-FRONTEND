package com.example.crewpass_frontend.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.telephony.VisualVoicemailSms
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.IDPW_Find.ClubIDPWFindActivity
import com.example.crewpass_frontend.IDPW_Find.FindIDPWDialog
import com.example.crewpass_frontend.IDPW_Find.PersonalIDPWFindActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.Retrofit.Club.LogIn.*
import com.example.crewpass_frontend.Retrofit.Personal.LogIn.Data
import com.example.crewpass_frontend.Retrofit.Club.LogIn.ClubLoginService
import com.example.crewpass_frontend.Retrofit.Personal.LogIn.*
import com.example.crewpass_frontend.SignUp.Club.ClubSignUpActivity
import com.example.crewpass_frontend.SignUp.Personal.PersonalSignUpActivity
import com.example.crewpass_frontend.SignUp.SignUpDialog
import com.example.crewpass_frontend.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), MyCustomDialogInterface,
    FindIDPWDialog.FindIDPWDialogInterface,
    SignUpDialog.SignUpDialogInterface, PersonalLoginResult, ClubLoginResult {
    lateinit var binding: ActivityLoginBinding
    lateinit var pref: SharedPreferences
    var string = ""
    var club_clicked = false
    var personal_clicked = false
    var choice_type: String = ""
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        binding.personalBtn.setOnClickListener {
            if (personal_clicked) {
                personal_clicked = !personal_clicked // 클릭 취소 상태로 변경해주기
                binding.personalBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            } else { // false
                personal_clicked = !personal_clicked // 클릭 상태로 변경해주기
                binding.personalBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if (club_clicked) {
                    club_clicked = !club_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.clubBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                string = "Personal"
            }
            println(personal_clicked.toString())
        }

        binding.clubBtn.setOnClickListener {
            if (club_clicked) { // 이미 누른 상태에서 다시 눌렀을 때
                club_clicked = !club_clicked // 클릭 취소 상태로 변경해주기
                binding.clubBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            } else {
                club_clicked = !club_clicked // 클릭 상태로 변경해주기
                binding.clubBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                if (personal_clicked) {
                    personal_clicked = !personal_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.personalBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
                string = "Club"
            }
            println(club_clicked.toString())
        }


        // 로그인 버튼
        binding.btnLogin.setOnClickListener {
            if (club_clicked == false && personal_clicked == false) {
                Toast.makeText(this, "로그인 타입을 선택해주세요(일반/동아리)", Toast.LENGTH_LONG).show()
            } else {
                if (string.equals("Personal")) {
                    val personalLoginService = PersonalLoginService()
                    personalLoginService.setLoginResult(this)
                    personalLoginService.login(
                        binding.edittextId.text.toString(),
                        binding.edittextPassword.text.toString()
                    )
                } else {
                    val clubLoginService = ClubLoginService()
                    clubLoginService.setLoginResult(this)
                    clubLoginService.login(
                        binding.edittextId.text.toString(),
                        binding.edittextPassword.text.toString()
                    )
                }
            }

        }


        // 회원가입 클릭
        binding.textSignUp.setOnClickListener {
            val signUpDialog = SignUpDialog(this, this)
            signUpDialog.start()
        }

        // 아이디 비밀번호 찾기
        binding.textFind.setOnClickListener {
            val findIDPWDialog = FindIDPWDialog(this, this)
            findIDPWDialog.start()
        }

        // 자동로그인
        val pref = getSharedPreferences("loginId", MODE_PRIVATE)
        val savedId = pref.getString("id", "").toString()
        val logined_id_get = pref.getInt("logined_id", -1)

        if(logined_id_get == -1){

        }else{
            logined_id = logined_id_get
        }
        Log.d("저장된 값", savedId)

        if(savedId.equals("")){

        }else{
            val intent = Intent(this, MainActivity::class.java)
            val type = savedId.substring(savedId.length-1)
            Log.d("현재 로그인 타입 : ", type)
            if(type.equals("c")){
                intent.putExtra("Key", "Club")
            }else{
                intent.putExtra("Key", "Personal")
            }
            Toast.makeText(this, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }

    }

    override fun onbtnGotoMainClicked() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Key", string)
        finish()
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

    override fun clubLoginSuccess(code: Int, data: ClubData) {
//        get_club()
        logined_id = data.crew_user_id
        saveData(data.loginId, "c", logined_id)
        Log.d("logined_id : ", logined_id.toString())
        val dlg = MyCustomDialog(this, this)
        dlg.show()
    }

    override fun clubLoginUpFailure(code: Int) {
        Log.d("LOGIN-FAILURE", "로그인 실패")
    }


    override fun personalLoginSuccess(
        code: Int,
        data: Data
    ) {
        logined_id = data.crew_user_id
        saveData(data.loginId, "p", logined_id)
        val dlg = MyCustomDialog(this, this)
        dlg.show()
    }

    override fun personalLoginUpFailure(code: Int) {
        Log.d("LOGIN-FAILURE", "로그인 실패")
    }

    override fun onFindPersonalButtonClicked() {
        val intent = Intent(this, PersonalIDPWFindActivity::class.java)
        startActivity(intent)
    }

    override fun onFindClubButtonClicked() {
        val intent = Intent(this, ClubIDPWFindActivity::class.java)
        startActivity(intent)
    }

    fun saveData(loginId : String, type : String, logined_id : Int){
        val pref = getSharedPreferences("loginId", MODE_PRIVATE)
        val edit = pref.edit() // 수정모드

        edit.putString("id", loginId + type)
        edit.putInt("logined_id", logined_id)
        edit.apply()
    }
}

var logined_id = 0