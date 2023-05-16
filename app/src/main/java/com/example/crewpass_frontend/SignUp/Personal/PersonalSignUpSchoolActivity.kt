package com.example.crewpass_frontend.SignUp.Personal

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.Retrofit.Personal.SignUp.SignUpResult
import com.example.crewpass_frontend.Retrofit.Personal.SignUp.SignUpService
import com.example.crewpass_frontend.SignUp.Personal.FindSchool.FindSchoolDialog
import com.example.crewpass_frontend.SignUp.Personal.FindSchool.school_name_selected
import com.example.crewpass_frontend.databinding.ActivityPersonalSignupSchoolBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PersonalSignUpSchoolActivity : AppCompatActivity(), FindSchoolDialog.CustomDialogInterface, SignUpResult {
    lateinit var binding: ActivityPersonalSignupSchoolBinding

    var personal_name = ""
    var personal_id = ""
    var personal_passwd = ""
    var email = ""
    var job  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalSignupSchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gubun = intent.getStringExtra("gubun")
        personal_name = intent.getStringExtra("personal_name")!!
        personal_id = intent.getStringExtra("personal_id")!!
        personal_passwd = intent.getStringExtra("personal_passwd")!!
        email = intent.getStringExtra("personal_email")!!
        var profile : Uri = intent.getParcelableExtra("profile")!!
        job = intent.getStringExtra("job")!!

        Log.d("vales : ", "personal_name: ${personal_name}, personal_id: ${personal_id}, personal_passwd: ${personal_passwd}, email: ${email}, job: ${job}")

        binding.btnFindSchool.setOnClickListener {
            val findSchoolDialog = FindSchoolDialog(this, this, gubun!!)
            findSchoolDialog.show()
        }

        binding.btnNext.setOnClickListener {
            signUp(profile)
        }

    }

    override fun onOkButtonClicked(school_name : String) {
        binding.edittextSchool.text = school_name_selected
    }

    fun signUp(profile : Uri){
        Log.d("profile_uri : ", profile.toString())

        var body : MultipartBody.Part? = null

        val file = File(absolutelyPath(profile, this))

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        body = MultipartBody.Part.createFormData("profile", file.name, requestFile)

        var school = binding.edittextSchool.text.toString()

        val signUpService = SignUpService()
        signUpService.signUp(personal_name, personal_id, personal_passwd, email, job, school ,body)
        signUpService.setSignUpResult(this)
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    override fun signUpSuccess(code: Int) {
        val intent = Intent(this, PersonalSignUpDoneActivity::class.java)

        startActivity(intent)
        overridePendingTransition(0,0)
    }

    override fun signUpFailure(code: Int) {
        Log.d("SIGNUP-FAILURE", "회원가입 실패")
    }
}