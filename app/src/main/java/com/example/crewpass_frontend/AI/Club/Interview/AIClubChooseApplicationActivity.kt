package com.example.crewpass_frontend.AI.Club.Interview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crewpass_frontend.Data.Application
import com.example.crewpass_frontend.databinding.ActivityPersonalChooseAnnouncementBinding
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class AIClubChooseApplicationActivity:AppCompatActivity() {
    lateinit var binding: ActivityPersonalChooseAnnouncementBinding
    lateinit var context: Context
    var application_list = ArrayList<Application>()
    lateinit var aiApplicationRVAdapter: AIApplicationRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        binding = ActivityPersonalChooseAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, AnalyzedApplicationActivity::class.java)
            startActivity(intent)
        }
    }

    fun initActionBar() {

        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "동아리 모집글 현황"
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

            aiApplicationRVAdapter = AIApplicationRVAdapter(application_list)
            binding.announcementListRv.adapter = aiApplicationRVAdapter
            binding.announcementListRv.layoutManager = LinearLayoutManager(context)
        }
    }
}