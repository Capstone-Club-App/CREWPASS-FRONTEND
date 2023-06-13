package com.example.crewpass_frontend.Home.Club

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.MainActivity
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.Club.Question.QuestionPostResult
import com.example.crewpass_frontend.Retrofit.Club.Question.QuestionService
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.PostResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentPostResponse
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentPostResult
import com.example.crewpass_frontend.Retrofit.Club.Recruitment.RecruitmentService
import com.example.crewpass_frontend.databinding.ActivityWriteQuestionBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class WriteQuestionActivity : AppCompatActivity(), RecruitmentPostResult, QuestionPostResult {
    var add_count = 4
    lateinit var binding: ActivityWriteQuestionBinding
    var question1Limit: Int = 0
    var question2Limit: Int = 0
    var question3Limit: Int = 0
    var question4Limit: Int? = 0
    var question5Limit: Int? = 0
    var question6Limit: Int? = 0
    var question7Limit: Int? = 0

    var isDeleted = 0
    var title = ""
    var deadline = ""
    var content = ""
    var image: Uri? = null

    var recruitment_id = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isDeleted = intent.getIntExtra("isDeleted", -1)
        title = intent.getStringExtra("title")!!
        deadline = intent.getStringExtra("deadline")!!
        content = intent.getStringExtra("content")!!
        image = intent.getParcelableExtra("image")!!

        initActionBar()

        binding.btnAddQuestion.setOnClickListener {
            add_count++
            if (add_count == 4) {
                binding.txtQuestion4.visibility = View.VISIBLE
                binding.edittextQuestion4.visibility = View.VISIBLE
                binding.spinnerQuestion4.visibility = View.VISIBLE
                binding.txtSpinner4.visibility = View.VISIBLE
            }
            if (add_count == 5) {
                binding.txtQuestion5.visibility = View.VISIBLE
                binding.edittextQuestion5.visibility = View.VISIBLE
                binding.spinnerQuestion5.visibility = View.VISIBLE
                binding.txtSpinner5.visibility = View.VISIBLE
            }
            if (add_count == 6) {
                binding.txtQuestion6.visibility = View.VISIBLE
                binding.edittextQuestion6.visibility = View.VISIBLE
                binding.spinnerQuestion6.visibility = View.VISIBLE
                binding.txtSpinner6.visibility = View.VISIBLE
            }
            if (add_count == 7) {
                binding.txtQuestion7.visibility = View.VISIBLE
                binding.edittextQuestion7.visibility = View.VISIBLE
                binding.btnAddQuestion.visibility = View.GONE
                binding.spinnerQuestion7.visibility = View.VISIBLE
                binding.txtSpinner7.visibility = View.VISIBLE
            }
        }

        setupSpinnerText()
        setupSpinnerHandler()

        binding.btnSubmit.setOnClickListener {
            if (binding.edittextQuestion1.text.toString().trim().isEmpty()){
                Toast.makeText(this, "질문1을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                if(binding.edittextQuestion2.text.toString().trim().isEmpty()){
                    Toast.makeText(this, "질문2를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else{
                    if(binding.edittextQuestion3.text.toString().trim().isEmpty()){
                        Toast.makeText(this, "질문3을 입력해주세요.", android.widget.Toast.LENGTH_SHORT).show()
                    }else{
                        if(binding.edittextQuestion4.text.toString().trim().isEmpty()){
                            Toast.makeText(this, "질문4를 입력해주세요.", Toast.LENGTH_SHORT).show()
                        }else{
                            if(binding.edittextQuestion5.visibility == View.VISIBLE && binding.edittextQuestion5.text.toString().trim().isEmpty()){
                                Toast.makeText(this, "질문5를 입력해주세요.", Toast.LENGTH_SHORT).show()
                            }else{
                                if(binding.edittextQuestion6.visibility == View.VISIBLE && binding.edittextQuestion6.text.toString().trim().isEmpty()){
                                    Toast.makeText(this, "질문6을 입력해주세요.", Toast.LENGTH_SHORT).show()
                                }else{
                                    if(binding.edittextQuestion7.visibility == View.VISIBLE && binding.edittextQuestion7.text.toString().trim().isEmpty()){
                                        Toast.makeText(this, "질문7을 입력해주세요.", Toast.LENGTH_SHORT).show()
                                    }else{
                                        postRecruitment(image!!)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSpinnerText() {
        val txt = resources.getStringArray(R.array.spinner_txt)
        val adapter = ArrayAdapter(this, R.layout.spinner_item_custom, txt)
        binding.spinnerQuestion1.adapter = adapter
        binding.spinnerQuestion2.adapter = adapter
        binding.spinnerQuestion3.adapter = adapter
        binding.spinnerQuestion4.adapter = adapter
        binding.spinnerQuestion5.adapter = adapter
        binding.spinnerQuestion6.adapter = adapter
        binding.spinnerQuestion7.adapter = adapter
    }


    private fun setupSpinnerHandler() {
        binding.spinnerQuestion1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question1Limit = 100
                    else if (position == 1)
                        question1Limit = 200
                    else if (position == 2)
                        question1Limit = 300
                    else if (position == 3)
                        question1Limit = 400
                    else
                        question1Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        binding.spinnerQuestion2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question2Limit = 100
                    else if (position == 1)
                        question2Limit = 200
                    else if (position == 2)
                        question2Limit = 300
                    else if (position == 3)
                        question2Limit = 400
                    else
                        question2Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.spinnerQuestion3.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question3Limit = 100
                    else if (position == 1)
                        question3Limit = 200
                    else if (position == 2)
                        question3Limit = 300
                    else if (position == 3)
                        question3Limit = 400
                    else
                        question3Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        binding.spinnerQuestion4.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question4Limit = 100
                    else if (position == 1)
                        question4Limit = 200
                    else if (position == 2)
                        question4Limit = 300
                    else if (position == 3)
                        question4Limit = 400
                    else
                        question4Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        binding.spinnerQuestion5.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question5Limit = 100
                    else if (position == 1)
                        question5Limit = 200
                    else if (position == 2)
                        question5Limit = 300
                    else if (position == 3)
                        question5Limit = 400
                    else
                        question5Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        binding.spinnerQuestion6.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question6Limit = 100
                    else if (position == 1)
                        question6Limit = 200
                    else if (position == 2)
                        question6Limit = 300
                    else if (position == 3)
                        question6Limit = 400
                    else
                        question6Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        binding.spinnerQuestion7.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0)
                        question7Limit = 100
                    else if (position == 1)
                        question7Limit = 200
                    else if (position == 2)
                        question7Limit = 300
                    else if (position == 3)
                        question7Limit = 400
                    else
                        question7Limit = 500
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 질문 작성"

        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    // 동아리 모집글 등록
    fun postRecruitment(image: Uri) {
        var body: MultipartBody.Part? = null
        val file = File(absolutelyPath(image, this))
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val recruitmentService = RecruitmentService()
        recruitmentService.setRecruitmentResult(this)
        recruitmentService.postRecruitment(logined_id, 0, title, deadline, content, body)
    }

    fun absolutelyPath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    override fun clubRecruitmentPostSuccess(code: Int, data: PostResult) {
        recruitment_id = data.recruitment_id
        Log.d("recruitment_id :", recruitment_id.toString())
        Toast.makeText(this, "모집글 등록이 완료되었습니다. 잠시 후에 질문 등록이 완료됩니다.", Toast.LENGTH_SHORT).show()
        postQuestion()
    }

    override fun clubRecruitmentPostFailure(code: Int) {
        Log.d("모집글 등록 실패", "")
    }

    // 질문 등록
    fun postQuestion() {
        if (binding.edittextQuestion4.visibility == View.GONE) {
            binding.edittextQuestion4.text = null
            question4Limit = null
        }
        if (binding.edittextQuestion5.visibility == View.GONE) {
            binding.edittextQuestion5.text = null
            question5Limit = null
        }
        if (binding.edittextQuestion6.visibility == View.GONE) {
            binding.edittextQuestion6.text = null
            question6Limit = null
        }
        if (binding.edittextQuestion7.visibility == View.GONE) {
            binding.edittextQuestion7.text = null
            question7Limit = null
        }

        val questionService = QuestionService()
        questionService.setQuestionPostResult(this)
        questionService.postQuestion(
            logined_id,
            recruitment_id,
            binding.edittextQuestion1.text.toString(),
            binding.edittextQuestion2.text.toString(),
            binding.edittextQuestion3.text.toString(),
            binding.edittextQuestion4.text.toString(),
            binding.edittextQuestion5.text.toString(),
            binding.edittextQuestion6.text.toString(),
            binding.edittextQuestion7.text.toString(),
            question1Limit,
            question2Limit,
            question3Limit, question4Limit, question5Limit, question6Limit, question7Limit
        )
    }

    override fun questionPostSuccess(code: Int) {
        intent.putExtra("finish", "finish")
        Toast.makeText(this, "질문 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun questionPostFailure(code: Int) {
        Log.d("질문 등록 실패", "")
    }
}