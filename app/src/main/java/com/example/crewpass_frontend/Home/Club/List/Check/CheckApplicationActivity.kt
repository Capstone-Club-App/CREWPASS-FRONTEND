package com.example.crewpass_frontend.Home.Club.List.Check

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.databinding.ActivityCheckApplicationBinding
import java.sql.Timestamp
import java.util.*

class CheckApplicationActivity:AppCompatActivity() {
    lateinit var binding: ActivityCheckApplicationBinding
    lateinit var context :Context
    var application_list = ArrayList<Application>()
    lateinit var clubApplicationRVAdapter: ClubApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        initActionBar()
        initRecyclerView()

        binding.btnSelectAll.setOnClickListener {

        }
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "지원서 확인"

        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }

    fun initRecyclerView() {
        var timestamp = Timestamp(Date().time)
        application_list.apply {
            add(
                Application(
                    1, timestamp,
                    "...", "...", "11", " ", " ", " ", " ",
                    3, 3, 2, 1, 1, 1, 1,
                    1, 1
                )
            )
            add(
                Application(
                    1, timestamp,
                    "...", "...", "11", " ", " ", " ", " ",
                    3, 3, 2, 1, 1, 1, 1,
                    1, 1
                )
            )
            add(
                Application(
                    1, timestamp,
                    "...", "...", "11", " ", " ", " ", " ",
                    3, 3, 2, 1, 1, 1, 1,
                    1, 1
                )
            )
            add(
                Application(
                    1, timestamp,
                    "...", "...", "11", " ", " ", " ", " ",
                    3, 3, 2, 1, 1, 1, 1,
                    1, 1
                )
            )
            add(
                Application(
                    1, timestamp,
                    "...", "...", "11", " ", " ", " ", " ",
                    3, 3, 2, 1, 1, 1, 1,
                    1, 1
                )
            )

            clubApplicationRVAdapter = ClubApplicationRVAdapter(application_list)
            binding.announcementListRv.adapter = clubApplicationRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
            clubApplicationRVAdapter.setItemClickListener(object :
                ClubApplicationRVAdapter.OnItemClickListener {
                override fun onItemClick(application: Application) {
                    val intent = Intent(context, ClubApplicationDetailActivity::class.java)
//                    intent.putExtra("application", true)
                    startActivity(intent) // 지원서 작성으로 이동
                }
            })
        }
    }
}