package com.example.crewpass_frontend.Home.Club

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.crewpass_frontend.databinding.ActivityWriteAnnouncementBinding
import java.util.*

class WriteRecruitmentActivity: AppCompatActivity() {
    lateinit var binding: ActivityWriteAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.btnWriteQuestion.setOnClickListener {
            val intent = Intent(this, WriteQuestionActivity::class.java)
            startActivity(intent)
        }

        binding.btnCalendar.setOnClickListener {
            showDatePicker()
        }

        binding.btnTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, y, m, d->

            binding.txtDeadlineDate.text = "$y-$m-$d"
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, h, m ->

            binding.txtDeadlineTime.text = "$h:$m"
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()
    }

    fun initActionBar(){
        binding.innerPageTop.appbarPageNameLeftTv.visibility = View.VISIBLE
        binding.innerPageTop.appbarPageNameLeftTv.text = "모집글 작성"
        binding.innerPageTop.appbarBackBtn.setOnClickListener{onBackPressed()}
    }
}