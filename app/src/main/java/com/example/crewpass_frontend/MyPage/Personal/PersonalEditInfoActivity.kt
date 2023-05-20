package com.example.crewpass_frontend.MyPage.Personal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalData
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalGetResult
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalPutResult
import com.example.crewpass_frontend.Retrofit.Personal.Personal.PersonalService
import com.example.crewpass_frontend.databinding.ActivityPersonalEditInfoBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PersonalEditInfoActivity : AppCompatActivity(), PersonalPutResult {
    lateinit var binding: ActivityPersonalEditInfoBinding
    lateinit var personalService: PersonalService
    var imagePath : Uri? = null
    private var PICK_IMAGE = 1
    var picture: MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personalService = PersonalService()
        Glide.with(this).load(intent.getStringExtra("profile"))
            .into(binding.profileImg)
        binding.edittextId.setText(intent.getStringExtra("userLoginId"))
        binding.edittextPassword.setText(intent.getStringExtra("userPw"))
        binding.edittextName.setText(intent.getStringExtra("userName"))
        binding.edittextEmail.setText(intent.getStringExtra("userEmail"))
        binding.edittextJob.setText(intent.getStringExtra("job"))
        binding.edittextSchool.setText(intent.getStringExtra("school"))

        initActionBar()

        binding.btnProfile.setOnClickListener {
            getImage()
        }

        binding.btnSave.setOnClickListener {
            putPersonal()
        }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "기본정보 편집"
        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    // 회원 정보 수정
    fun putPersonal() {
        personalService.setPersonalPutResult(this)

        val pref = this.getPreferences(MODE_PRIVATE)
        if(imagePath == null){
            val file = File(absolutelyPath(Uri.parse(pref.getString("crew_profile_uri","")), this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            Log.d("파일 생성!! ======== ", file.name)
            picture = body
        }

        personalService.putPersonal(
            logined_id.toString(),
            binding.edittextName.text.toString(),
            binding.edittextPassword.text.toString(),
            binding.edittextEmail.text.toString(),
            binding.edittextJob.text.toString(),
            binding.edittextSchool.text.toString(),
            picture!!
        )
    }

    override fun personalPutSuccess(code: Int) {
        Log.d("회원 정보 수정 성공", "")
        finish()
    }

    override fun personalPutFailure(code: Int) {
        Log.d("회원 정보 수정 실패", "")
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
        if (resultCode == Activity.RESULT_OK) {
            // 사진 가져오는 부분
            imagePath = data!!.data

            val file = File(absolutelyPath(imagePath, this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            Log.d("파일 생성!! ======== ", file.name)
            picture = body

            // sharedPreference에 기존 profile 저장해주기
            val sharedPreference = getSharedPreferences("user_profile", MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("user_profile_uri", imagePath.toString())
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
}