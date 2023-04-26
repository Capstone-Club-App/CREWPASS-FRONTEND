package com.example.crewpass_frontend.SignUp.Personal.FindSchool

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.Retrofit.FindSchool.*

class FindSchoolDialog(context: Context, Interface: CustomDialogInterface, gubun : String) : Dialog(context), SchoolGetResult {

    private var customDialogInterface: CustomDialogInterface = Interface

    private lateinit var okButton : AppCompatButton
    private lateinit var cancelButton : AppCompatButton
    private lateinit var btn_find_school : AppCompatButton
    private lateinit var edittext_school : EditText
    private lateinit var school_list_rv : RecyclerView
    var querys = HashMap<String, String>()
    var gubun = gubun
    var school_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dlg_find_school)

        okButton = findViewById(R.id.dlg_btn_ok)
        cancelButton = findViewById(R.id.dlg_btn_cancel)
        btn_find_school = findViewById(R.id.btn_find_school)
        edittext_school = findViewById(R.id.dlg_edittext_school)
        school_list_rv = findViewById(R.id.school_list_rv)


        btn_find_school.setOnClickListener {
            getSchool()
        }

        okButton.setOnClickListener {
            customDialogInterface.onOkButtonClicked(school_name)
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    interface CustomDialogInterface {
        fun onOkButtonClicked(school_name : String)

    }

    fun getQuery() : HashMap<String, String>{
        querys.put("apiKey", "512f86550fa92f3dcbeb00a74877ae8e")
        querys.put("svcType", "api")
        querys.put("svcCode", "SCHOOL")
        querys.put("contentType", "json")
        querys.put("gubun", gubun)
        querys.put("searchSchulNm", edittext_school.text.toString())

        return querys
    }
    fun getSchool(){
        val schoolGetService = SchoolGetService()
        schoolGetService.setChatGetResult(this)
        schoolGetService.getSchool(getQuery())

    }

    override fun getSchoolSuccess(result:Content) {
        if(result == null){
            Log.d("null 들어옴", "")
        }else {
            val schoolRVAdapter = SchoolRVAdapter()
            schoolRVAdapter.setItem(result.dataList)
            school_list_rv.adapter = schoolRVAdapter
            school_list_rv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun getSchoolFailure(code: Int, message: String) {
        Log.d("실패", "")
    }
}