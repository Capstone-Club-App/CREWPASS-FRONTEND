package com.example.crewpass_frontend.Home.Club

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crewpass_frontend.databinding.ActivityWriteAnnouncementBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class WriteRecruitmentActivity: AppCompatActivity() {
    lateinit var binding: ActivityWriteAnnouncementBinding
    private var PICK_IMAGE = 1
    var picture : MultipartBody.Part? = null
    var image_uri : Uri? = null
    var str_date = ""
    var str_time = ""
    var timestamp : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnImage.setOnClickListener {
            getImage()
        }

        binding.btnCalendar.setOnClickListener {
            showDatePicker()
        }

        binding.btnTime.setOnClickListener {
            showTimePicker()
        }

        binding.btnWriteQuestion.setOnClickListener {
            val intent = Intent(this, WriteQuestionActivity::class.java)

            val time = binding.txtDeadlineDate.text.toString() + " "+binding.txtDeadlineTime.text.toString() + ":30"

            intent.putExtra("isDeleted", 0)
            intent.putExtra("title", binding.edittextTitle.text.toString())
            intent.putExtra("deadline", time)
            intent.putExtra("content", binding.edittextContent.text.toString())
            intent.putExtra("image", image_uri)

            startActivity(intent)
        }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, y, m, d->

//            binding.txtDeadlineDate.text = "$y-$m-$d"
            if(m < 10){
                if(d<10)
                    binding.txtDeadlineDate.text = "$y-0$m-0$d"
                else
                    binding.txtDeadlineDate.text = "$y-0$m-$d"
            }
            else{
                if(d<10)
                    binding.txtDeadlineDate.text = "$y-$m-0$d"
                else
                    binding.txtDeadlineDate.text = "$y-$m-$d"
            }

        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, h, m ->

//            binding.txtDeadlineTime.text = "$h:$m"
            if(h<10){
                if(m<10)
                    binding.txtDeadlineTime.text = "0$h-0$m"
                else
                    binding.txtDeadlineTime.text = "0$h-$m"
            }
            else{
                if(m<10)
                    binding.txtDeadlineTime.text = "$h-0$m"
                else
                    binding.txtDeadlineTime.text = "$h-$m"
            }

        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 작성"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
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
            image_uri = imagePath

            val file = File(absolutelyPath(imagePath, this))
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            picture = body

            Glide.with(this).load(imagePath)
                .into(binding.imageViewImage)

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