package com.example.crewpass_frontend.Home.Club.List.Check

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.StructCmsghdr
import android.util.Log
import android.view.View
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

class CheckApplicationActivity : AppCompatActivity(), ApplicationGetResult, PassNpassResult {
    lateinit var binding: ActivityCheckApplicationBinding
    lateinit var context: Context
    var question_id: Int = 0
    var crew_name : String = ""
    var application_list = ArrayList<ApplicationData>()
    var pass_npass = ""
    lateinit var clubApplicationRVAdapter: ClubApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        question_id = intent.getIntExtra("question_id", -1)
        Log.d("question_id : $question_id", "")
        crew_name = intent.getStringExtra("crew_name")!!

        initActionBar()

        binding.btnSelectAll.setOnClickListener {

        }

        binding.btnPass.setOnClickListener {
            pass_npass = "pass"
            postPass()
        }

        binding.btnFail.setOnClickListener {
            pass_npass = "npass"
            postNpass()
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
        initRecyclerView(data)
    }

    override fun applicationGetFailure(code: Int) {
        Log.d("지원서 조회 실패", "")
    }

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

    // 합불 통보하기
    fun postPass(){
        var userId = ""
        check_list.forEach {
            userId += it + ", "
        }
        userId = userId.substring(0, userId.length - 2)
        val msg = binding.edittextPass.text.toString()
        Log.d("msg : $msg", "")

        val applicationService = ApplicationService()
        applicationService.setPassNpassResult(this)
        applicationService.postPassNpass(crew_name, userId, msg)
    }

    // 합불 통보하기
    fun postNpass(){
        var userId = ""
        check_list.forEach {
            userId += it + ", "
        }
        userId = userId.substring(0, userId.length - 2)
        var msg = binding.edittextFail.text.toString()

        val applicationService = ApplicationService()
        applicationService.setPassNpassResult(this)
        applicationService.postPassNpass(crew_name, userId, msg)
    }

//    fun checkPass(check_list : ArrayList<String>){
//        for (application in application_list) {
//            if(check_list.contains(application.user_id.toString())){
//                var position = findItemPositionByName(application.user_id)
//
//            }
//        }
//    }
//
//    fun findItemPositionByName(user_id: Int): Int {
//        for (i in items.indices) {
//            if (items[i].user_id == user_id) {
//                return i
//            }
//        }
//        return -1 // 해당 아이템을 찾지 못한 경우 -1을 반환합니다.
//    }

    override fun passNpassSuccess(code: Int) {
        Log.d("합불통보 성공", "")
        clubApplicationRVAdapter.checkPass(check_list)

        val layoutManager = binding.announcementListRv.layoutManager as LinearLayoutManager
        val adapter = binding.announcementListRv.adapter as ClubApplicationRVAdapter

        val itemCount = adapter.itemCount
        for(position in 0 until itemCount){
            val viewHolder =
                binding.announcementListRv.findViewHolderForAdapterPosition(position) as ClubApplicationRVAdapter.ViewHolder
            if(viewHolder != null){
                if(pass_npass.equals("pass")){
                    for(pos in clubApplicationRVAdapter.position_list){
                        if (viewHolder.adapterPosition == pos){
                            viewHolder.binding.itemCheckBox.visibility = View.INVISIBLE
                            viewHolder.binding.itemTxtPass.visibility = View.VISIBLE
                        }
                    }
//                    if(check_list.contains(application_list[position].user_id.toString())) {
//                        viewHolder.binding.itemCheckBox.visibility = View.INVISIBLE
//                        viewHolder.binding.itemTxtPass.visibility = View.VISIBLE
//                    }
                }
                else{
                    if(!check_list.contains(application_list[position].user_id.toString())) {
                        viewHolder.binding.itemCheckBox.visibility = View.INVISIBLE
                        viewHolder.binding.itemTxtNpass.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun passNpassFailure(code: Int) {
        Log.d("합불통보 실패", "")
    }
}
var check_list = ArrayList<String>()