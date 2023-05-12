package com.example.crewpass_frontend.SignUp.Club

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Data.SignUp_Club
import com.example.crewpass_frontend.Retrofit.Club.SignUp.SignUpResult
import com.example.crewpass_frontend.Retrofit.Club.SignUp.SignUpService
import com.example.crewpass_frontend.databinding.ActivityClubSignupEmailBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

class ClubSignUpEmailActivity : AppCompatActivity(), SignUpResult {
    lateinit var binding: ActivityClubSignupEmailBinding
    var club_name = ""
    var club_id = ""
    var club_passwd = ""
    var region1 = ""
    var region2 = ""
    var field1 = ""
    var field2  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubSignupEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 값 가져오기
        club_name = intent.getStringExtra("club_name")!!
        club_id = intent.getStringExtra("club_id")!!
        club_passwd = intent.getStringExtra("club_passwd")!!
        region1 = intent.getStringExtra("region1")!!
        region2 = intent.getStringExtra("region2")!!
        field1 = intent.getStringExtra("field1")!!
        field2 = intent.getStringExtra("field2")!!
        var profile : Uri = intent.getParcelableExtra("profile")!!

        Log.d("vales : ", "club_name: ${club_name}, club_id: ${club_id}, club_passwd: ${club_passwd}, region1: ${region1}, region2: ${region2}" +
                ", field1: ${field1}, field2: ${field2}")

        binding.btnNext.setOnClickListener {
            signUp(profile)
        }
    }

    fun signUp(profile : Uri){
        Log.d("profile_uri : ", profile.toString())

        var body : MultipartBody.Part? = null

        val file = File(absolutelyPath(profile, this))

//        var inputStream : InputStream? = null
//
//        try{
//            inputStream = this.contentResolver.openInputStream(profile)
//        }catch (e : IOException){
//            e.printStackTrace()
//        }
//
//        val bitmap = BitmapFactory.decodeStream(inputStream)
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG,20, byteArrayOutputStream)
//
//        val requestBody = RequestBody.create(MediaType.parse("image/*"),byteArrayOutputStream.toByteArray())
//        val uploadFile = MultipartBody.Part.createFormData("profile", file.getName() ,requestBody)

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        body = MultipartBody.Part.createFormData("profile", file.name, requestFile)

        Log.d("파일 이름 : ", file.name)
        Log.d("body- profile : ", body.toString())

        var masterEmail = binding.edittextChairman.text.toString()
        var subEmail = binding.edittextViceChairman.text.toString()

        val signUpService = SignUpService()
        signUpService.signUp(club_name, club_id, club_passwd, region1, region2, field1, field2, masterEmail, subEmail ,body)
        signUpService.setSignUpResult(this)
    }

    override fun signUpSuccess(code: Int) {
        val intent = Intent(this, ClubSignUpDoneActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
    }

    override fun signUpFailure(code: Int) {
        Log.d("SIGNUP-FAILURE", "회원가입 실패")
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }
}