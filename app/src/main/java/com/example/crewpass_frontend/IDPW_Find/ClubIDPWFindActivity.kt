package com.example.crewpass_frontend.IDPW_Find

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Retrofit.Club.FindPWID.*
import com.example.crewpass_frontend.databinding.ActivityClubIdPwFindBinding

class ClubIDPWFindActivity : AppCompatActivity(),
    FindIDEmailClubNameResult, FindIDNumbResult, FindPWEmailResult, FindPWNumbResult {
    lateinit var binding: ActivityClubIdPwFindBinding
    lateinit var findIDPWService: FindIDPWService
    var certificateNumberGet = -1
    var id_clicked = true
    var pw_clicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubIdPwFindBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findIDPWService = FindIDPWService()

        binding.IDBtn.setOnClickListener {
            if (id_clicked) {
                id_clicked = !id_clicked // 클릭 취소 상태로 변경해주기
                binding.IDBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            } else { // false
                id_clicked = !id_clicked // 클릭 상태로 변경해주기
                binding.IDBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                binding.layoutId.visibility = View.VISIBLE
                binding.layoutPw.visibility = View.INVISIBLE
                binding.txtCotentPw1.visibility = View.INVISIBLE
                binding.txtCotentPw2.visibility = View.INVISIBLE
                binding.txtPwFindDone.visibility = View.INVISIBLE
                binding.txtResultPw.visibility = View.INVISIBLE
                if (pw_clicked) {
                    pw_clicked = !pw_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.PWBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
            }
        }

        binding.PWBtn.setOnClickListener {
            if (pw_clicked) { // 이미 누른 상태에서 다시 눌렀을 때
                pw_clicked = !pw_clicked // 클릭 취소 상태로 변경해주기
                binding.PWBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
            } else {
                pw_clicked = !pw_clicked // 클릭 상태로 변경해주기
                binding.PWBtn.setBackgroundColor(Color.parseColor("#6DA4FE"))
                binding.layoutId.visibility = View.INVISIBLE
                binding.layoutPw.visibility = View.VISIBLE
                binding.txtCotentId.visibility = View.INVISIBLE
                binding.txtPwFindDone.visibility = View.INVISIBLE
                binding.txtResultId.visibility = View.INVISIBLE
                if (id_clicked) {
                    id_clicked = !id_clicked // 동아리 회원 클릭상태일 때 변경해주기!!
                    binding.IDBtn.setBackgroundColor(Color.parseColor("#F4F4F4"))
                }
            }
        }

        // 아이디 찾기
        binding.btnSendNumberId.setOnClickListener {
            if (binding.edittextClubName.text.toString().trim().isEmpty()){
                Toast.makeText(this, "동아리명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                if(binding.edittextEmailId.text.toString().trim().isEmpty()){
                    Toast.makeText(this, "동아리 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else{
                    findIdSendEmail()
                }
            }
        }

        binding.btnFindId.setOnClickListener {
            if (binding.edittextClubName.text.toString().trim().isEmpty()){
                Toast.makeText(this, "동아리명을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                if(binding.edittextEmailId.text.toString().trim().isEmpty()){
                    Toast.makeText(this, "동아리 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else{
                    if (binding.edittextNumberId.text.toString().trim().isEmpty()){
                        Toast.makeText(this, "전송된 인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }else{
                        findId()
                    }
                }
            }
        }


        // 패스워드 찾기
        binding.btnSendNumberPw.setOnClickListener {
            findPWSendEmail()
        }

        binding.btnFindPw.setOnClickListener {
            findPW()
        }


        binding.btnLogin.setOnClickListener {
            finish()
        }
    }

    fun findIdSendEmail() {
        findIDPWService.setFindIDEmailClubNameResult(this)
        findIDPWService.findIDByEmailClubName(
            binding.edittextClubName.text.toString(),
            binding.edittextEmailId.text.toString()
        )
    }

    override fun findIdEmailClubNameSuccess(code: Int, data: ResultEmail) {
        Log.d("certificateNumberGet : ${data.certificateNumb}", "")
        Toast.makeText(this, "인증번호가 전송되었습니다. 이메일을 확인해주세요.", Toast.LENGTH_SHORT).show()
        certificateNumberGet = data.certificateNumb
    }

    override fun findIdEmailClubNameFailure(code: Int) {
        Toast.makeText(this, "인증번호가 전송에 실패하였습니다. 정확한 정보를 입력했는지 확인해주시길 바랍니다.", Toast.LENGTH_SHORT).show()
        Log.d("아이디 이메일 인증번호 전송 실패", "")
    }

    // 인증번호 입력 - 아이디
    fun findId() {
        findIDPWService.setFindIDNumbResult(this)
        findIDPWService.findIDByNumb(
            binding.edittextClubName.text.toString(),
            binding.edittextEmailId.text.toString(),
            certificateNumberGet,
            binding.edittextNumberId.text.toString().toInt()
        )
    }

    override fun findIdNumbSuccess(code: Int, data: ResultNumb) {
        binding.txtCotentId.visibility = View.VISIBLE
        binding.txtResultId.text = data.loginId
        binding.txtResultId.visibility = View.VISIBLE
        binding.txtIdFindDone.visibility = View.VISIBLE
    }

    override fun findIdNumbFailure(code: Int) {
        Log.d("아이디 찾기 실패", "")
    }

    // 아이디 찾기 이메일 인증
    fun findPWSendEmail() {
        findIDPWService.setFindPWEmailResult(this)
        findIDPWService.findPWByEmail(
            binding.edittextEmailPw.text.toString(),
            binding.edittextId.text.toString()
        )
    }

    override fun findIdPWSuccess(code: Int, data: ResultEmail) {
        Toast.makeText(this, "인증번호가 전송되었습니다. 이메일을 확인해주세요.", Toast.LENGTH_SHORT).show()
        certificateNumberGet = data.certificateNumb
    }

    override fun findIdPWFailure(code: Int) {
        Toast.makeText(this, "인증번호가 전송에 실패하였습니다. 정확한 정보를 입력했는지 확인해주시길 바랍니다.", Toast.LENGTH_SHORT).show()
        Log.d("비밀번호 이메일 인증번호 전송 실패", "")
    }

    fun findPW() {
        findIDPWService.setFindPWNumbResult(this)
        findIDPWService.findPWByNumb(
            binding.edittextId.text.toString(),
            binding.edittextEmailPw.text.toString(),
            certificateNumberGet,
            binding.edittextNumberPw.text.toString().toInt()
        )
    }

    override fun findNumbPWSuccess(code: Int, data: ResultNumb) {
        binding.txtCotentPw1.visibility = View.VISIBLE
        binding.txtCotentPw2.visibility = View.VISIBLE
        binding.txtResultPw.text = data.password
        binding.txtResultPw.visibility = View.VISIBLE
        binding.txtPwFindDone.visibility = View.VISIBLE
    }

    override fun findNumbPWFailure(code: Int) {
        Log.d("비밀번호 찾기 실패", "")
        Toast.makeText(this, "인증번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
    }

}