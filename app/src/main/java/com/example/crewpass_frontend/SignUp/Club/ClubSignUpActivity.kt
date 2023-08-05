package com.example.crewpass_frontend.SignUp.Club

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.Club.SignUp.CheckDuplicateCrewIDResult
import com.example.crewpass_frontend.Retrofit.Club.SignUp.CheckDuplicateCrewNameResult
import com.example.crewpass_frontend.Retrofit.Club.SignUp.SignUpService
import com.example.crewpass_frontend.databinding.ActivityClubSignupBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.Serializable


class ClubSignUpActivity : AppCompatActivity(), CheckDuplicateCrewNameResult, CheckDuplicateCrewIDResult {
    lateinit var binding: ActivityClubSignupBinding
    private var PICK_IMAGE = 1
    var picture : MultipartBody.Part? = null
    var profile_uri : Uri? = null
    var picture_name : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener {
            Log.d("click", "")
            getImage()
        }

        binding.btnNameCompare.setOnClickListener {
            val signUpService = SignUpService()
            signUpService.setCheckDuplicateCrewNameResult(this)
            signUpService.checkDuplicateCrewName(binding.edittextName.text.toString())
        }

        binding.btnIdCompare.setOnClickListener {
            val signUpService = SignUpService()
            signUpService.setCheckDuplicateCrewIDResult(this)
            signUpService.checkDuplicateCrewID(binding.edittextId.text.toString())
        }

        binding.edittextPasswordCheck.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
                // 우린 이 함수를 사용한다.
                checkPassword()
            }
        })

        binding.btnNext.setOnClickListener {
            if(picture == null){
                Toast.makeText(this, "사진을 선택해주세요.", Toast.LENGTH_SHORT).show()
            }else {
                if (binding.edittextName.text.toString().trim().isEmpty()){
                    Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else{
                   if(binding.txtIdUnusable.visibility == View.INVISIBLE && binding.txtIdUsable.visibility == View.INVISIBLE){
                       Toast.makeText(this, "이름 중복확인검사를 진행해주세요.", Toast.LENGTH_SHORT).show()
                   }else{
                       if(binding.txtIdUnusable.visibility == View.VISIBLE){
                           Toast.makeText(this, "중복된 이름입니다. 다른 이름으로 변경해주세요.", Toast.LENGTH_SHORT).show()
                       }else{
                           if(binding.edittextId.text.toString().trim().isEmpty()){
                               Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                           }else{
                               if(binding.txtIdUsable.visibility == View.INVISIBLE && binding.txtIdUnusable.visibility == View.INVISIBLE){
                                   Toast.makeText(this, "아이디 중복확인검사를 진행해주세요.", Toast.LENGTH_SHORT).show()
                               }else{
                                   if(binding.txtIdUnusable.visibility == View.VISIBLE){
                                       Toast.makeText(this, "중복된 아이디입니다. 다른 아이디로 변경해주세요.", Toast.LENGTH_SHORT).show()
                                   }else{
                                       if (binding.edittextPassword.text.toString().trim().isEmpty()){
                                           Toast.makeText(this, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                                       }else{
                                           if(binding.edittextPasswordCheck.text.toString().trim().isEmpty()){
                                               Toast.makeText(this, "패스워드 확인을 진행해주세요.", Toast.LENGTH_SHORT).show()
                                           }else{
                                               if(binding.txtPwNotCorrect.visibility == View.VISIBLE){
                                                   Toast.makeText(this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                                               }else{
                                                   val intent = Intent(this, ClubSignUpRegionActivity::class.java)
                                                   intent.putExtra("club_name", binding.edittextName.text.toString())
                                                   intent.putExtra("club_id", binding.edittextId.text.toString())
                                                   intent.putExtra("club_passwd", binding.edittextPassword.text.toString())
                                                   if(profile_uri == null){
                                                       profile_uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.img_basic_profile);
                                                   }else{
                                                       intent.putExtra("profile", profile_uri)
                                                   }

                                                   startActivity(intent)
                                                   overridePendingTransition(0,0)
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                       }
                   }
                }
            }
        }
    }

    fun checkPassword():Boolean{
        var password = binding.edittextPassword.text.toString().trim() //공백제거
        var passwordValidation = binding.edittextPasswordCheck.text.toString().trim()

        if (password == passwordValidation) {
            //패스워드와 패스워드 체크가 같은 경우
            binding.txtPwNotCorrect.visibility = View.INVISIBLE
            return true
        } else {
            binding.txtPwNotCorrect.visibility = View.VISIBLE
            return false
        }
    }

    // 이미지 가져오기
    fun getImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.type = "image/*" // 모든 이미지
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 돌려받은 resultCode가 정상인지 체크
        if(resultCode == Activity.RESULT_OK){
            // 사진 가져오는 부분
            val imagePath = data!!.data
            profile_uri = imagePath
            Log.d("profile_uri : ", profile_uri.toString())

            val file = File(absolutelyPath(imagePath, this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            Log.d("파일 생성!! ======== ", file.name)
            picture = body
            picture_name = file.name

            // sharedPreference에 기존 profile 저장해주기
            val sharedPreference = getSharedPreferences("crew_profile", MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("crew_profile_uri", imagePath.toString())
            editor.commit()

            Glide.with(this).load(imagePath)
                .circleCrop()
                .into(binding.profileImg)

        }
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    override fun usableCrewName(code: Int) {
        binding.txtNameUsable.visibility = View.VISIBLE
    }

    override fun unusableCrewName(code: Int) {
        binding.txtNameUnusable.visibility = View.VISIBLE
    }

    override fun usableCrewID(code: Int) {
        binding.txtIdUsable.visibility = View.VISIBLE
    }

    override fun unusableCrewID(code: Int) {
        binding.txtIdUnusable.visibility = View.VISIBLE
    }
}
