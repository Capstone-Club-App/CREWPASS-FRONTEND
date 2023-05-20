package com.example.crewpass_frontend.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.IDPW_Find.IDPWFindActivity
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubGetResult
import com.example.crewpass_frontend.Retrofit.Club.Club.ClubService
import com.example.crewpass_frontend.Retrofit.Club.LogIn.*
import com.example.crewpass_frontend.Retrofit.Personal.LogIn.Data
import com.example.crewpass_frontend.Retrofit.Club.LogIn.ClubLoginService
import com.example.crewpass_frontend.Retrofit.Personal.LogIn.*
import com.example.crewpass_frontend.SignUp.Club.ClubSignUpActivity
import com.example.crewpass_frontend.SignUp.Personal.PersonalSignUpActivity
import com.example.crewpass_frontend.SignUp.SignUpDialog
import com.example.crewpass_frontend.databinding.ActivityLoginBinding

class LoginActivity:AppCompatActivity(), MyCustomDialogInterface, SignUpDialog.SignUpDialogInterface, PersonalLoginResult, ClubLoginResult{
    lateinit var binding: ActivityLoginBinding
    lateinit var pref : SharedPreferences
    var string = ""
    var club_clicked = false
    var personal_clicked = false
    var choice_type : String = ""
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        pref = PreferenceManager.getDefaultSharedPreferences(this)

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
            if(club_clicked == false && personal_clicked == false){
                Toast.makeText(this, "로그인 타입을 선택해주세요(일반/동아리)", Toast.LENGTH_LONG)
            }
            else{
                if(string.equals("Personal")){
                    val personalLoginService = PersonalLoginService()
                    personalLoginService.setLoginResult(this)
                    personalLoginService.login(binding.edittextId.text.toString(), binding.edittextPassword.text.toString())
                }
                else{
                    val clubLoginService = ClubLoginService()
                    clubLoginService.setLoginResult(this)
                    clubLoginService.login(binding.edittextId.text.toString(), binding.edittextPassword.text.toString())
                }
            }

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
//        finish()
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

    override fun clubLoginSuccess(code: Int, data : ClubData) {
//        get_club()
        logined_id = data.crew_user_id
        Log.d("logined_id : ", logined_id.toString())
        val dlg = MyCustomDialog(this, this)
        dlg.show()

//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("Key", string)
//        startActivity(intent)
    }

    override fun clubLoginUpFailure(code: Int) {
        Log.d("LOGIN-FAILURE", "로그인 실패")
    }

//    fun get_club(){
//        val clubService = ClubService()
//        clubService.setRecruitmentResult(this)
//        clubService.getClub()
//    }


//    override fun clubGetSuccess(code: Int, data: com.example.crewpass_frontend.Retrofit.Club.Club.ClubData) {
//        Log.d("동아리 정보 불러오기 성공", "")
//        logined_id = data.crew_id
//        Log.d("logined_id : ", logined_id.toString())
//    }
//
//    override fun clubGetFailure(code: Int) {
//        Log.d("동아리 정보 불러오기 실패", "")
//    }


    override fun personalLoginSuccess(
        code: Int,
        data: Data
    ) {
        logined_id = data.crew_user_id
        val dlg = MyCustomDialog(this, this)
        dlg.show()
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("Key", string)
//        startActivity(intent)
    }

    override fun personalLoginUpFailure(code: Int) {
        Log.d("LOGIN-FAILURE", "로그인 실패")
    }
}
var logined_id = 0