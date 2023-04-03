package com.example.crewpass_frontend.SignUp.Personal

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.crewpass_frontend.R

class FindSchoolDialog(context : Context) {
    private val dlg = Dialog(context)
    private lateinit var ok_btn : AppCompatButton
    private lateinit var cancel_btn : AppCompatButton

    fun start(){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dlg_find_school)
        dlg.setCancelable(false)

        ok_btn = dlg.findViewById(R.id.dlg_btn_ok)
        cancel_btn = dlg.findViewById(R.id.dlg_btn_cancel)

        ok_btn.setOnClickListener {
            // 정보 보내주기
        }

        cancel_btn.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
}