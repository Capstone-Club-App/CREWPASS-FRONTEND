package com.example.crewpass_frontend.Home.Club.List.Check

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.StructCmsghdr
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationData
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationGetResult
import com.example.crewpass_frontend.Retrofit.Club.Application.ApplicationService
import com.example.crewpass_frontend.Retrofit.Club.Application.PassNpassResult
import com.example.crewpass_frontend.databinding.ActivityCheckApplicationBinding
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class CheckApplicationActivity : AppCompatActivity(), ApplicationGetResult, PassNpassResult,
    PassDialog.PassDialogInterface,
    NPassDialog.NPassDialogInterface,
    CheckSendDialog.CheckSendDialogInterface{
    lateinit var binding: ActivityCheckApplicationBinding
    lateinit var context: Context
    var question_id: Int = 0
    var crew_name : String = ""
    var application_list = ArrayList<ApplicationData>()
    var recruitment_id : Int = -1

    lateinit var checkSendDialog: CheckSendDialog
    lateinit var passDialog: PassDialog
    lateinit var nPassDialog: NPassDialog

    lateinit var clubApplicationRVAdapter: ClubApplicationRVAdapter

    var pass_list = ArrayList<Int>()
    var nPass_list = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        question_id = intent.getIntExtra("question_id", -1)
        Log.d("question_id : $question_id", "")
        crew_name = intent.getStringExtra("crew_name")!!
        recruitment_id = intent.getIntExtra("recruitment_id", -1)

        initActionBar()

        binding.btnSelectAll.setOnClickListener {

        }

        checkSendDialog = CheckSendDialog(this, this)
        passDialog = PassDialog(this, this)
        nPassDialog = NPassDialog(this, this)

        binding.btnPass.setOnClickListener {
            passDialog.start()
        }

        binding.btnFail.setOnClickListener {
            nPassDialog.start()
        }
    }

    override fun onResume() {
        super.onResume()
        getApplicaitonList()
    }

    fun initActionBar() {
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 확인"

        binding.innerPageTop.appbarBackBtn.setOnClickListener { onBackPressed() }
    }

    // 지원서 목록 가져오기
    fun getApplicaitonList() {
        val applicationService = ApplicationService()
        applicationService.setApplicationGetResult(this)
        applicationService.getApplication(question_id)
    }

    override fun applicationGetSuccess(code: Int, data: ArrayList<ApplicationData>) {
        application_list = data
        //initRecyclerView(data)
        initRecyclerView(data)
    }

    override fun applicationGetFailure(code: Int) {
        Log.d("지원서 조회 실패", "")
    }

//    fun initRecyclerView(data: ArrayList<ApplicationData>) {
//        clubApplicationRVAdapter = ClubApplicationRVAdapter(data)
//        binding.announcementListRv.adapter = clubApplicationRVAdapter
//        binding.announcementListRv.layoutManager = LinearLayoutManager(context)
//        clubApplicationRVAdapter.setItemClickListener(object :
//            ClubApplicationRVAdapter.OnItemClickListener {
//            override fun onItemClick(application: ApplicationData) {
//                val intent = Intent(context, ClubApplicationDetailActivity::class.java)
//                intent.putExtra("application_id", application.application_id)
//                startActivity(intent)
//            }
//        })
//    }

    fun initRecyclerView(data: ArrayList<ApplicationData>) {
        clubApplicationRVAdapter = ClubApplicationRVAdapter(data)
        binding.announcementListRv.adapter = clubApplicationRVAdapter
        binding.announcementListRv.layoutManager = LinearLayoutManager(context)
        clubApplicationRVAdapter.setItemClickListener(object :
            ClubApplicationRVAdapter.OnItemClickListener {
            override fun onItemClick(application: ApplicationData) {
                val intent = Intent(context, ClubApplicationDetailActivity::class.java)
                intent.putExtra("application_id", application.application_id)
                startActivity(intent)
            }
        })
    }


    // 합격 발표 다이얼로그
    override fun onPassSendButtonClicked(text : String) {
        checkSendDialog.start(true, text)
    }

    // 불합격 발표 다이얼로그
    override fun onNPassSendButtonClicked(text: String) {
        checkSendDialog.start(false, text)
    }

    // 결과 전송 다이얼로그
    override fun onOKButtonClicked(isPass : Boolean, msg: String) {
        if(isPass)
            postPass(msg)
        else
            postNpass(msg)
    }


    // 합불 통보하기
    fun postPass(msg : String){
        val applicationService = ApplicationService()
        applicationService.setPassNpassResult(this)
        applicationService.postPass(recruitment_id, crew_name, check_list, msg)
    }

    // 합불 통보하기
    fun postNpass(msg : String){
        val applicationService = ApplicationService()
        applicationService.setPassNpassResult(this)
        applicationService.postNpass(recruitment_id, crew_name, check_list, msg)
    }


    override fun passNpassSuccess(code: Int) {
        Log.d("합불통보 성공", "")
        Toast.makeText(this, "메세지 전송이 완료되었습니다.", Toast.LENGTH_LONG).show()
        getApplicaitonList()
    }

    override fun passNpassFailure(code: Int) {
        Toast.makeText(this, "메세지 전송에 실패하였습니다.", Toast.LENGTH_LONG).show()
        Log.d("합불통보 실패", "")
    }
}
var check_list = ArrayList<Int>()