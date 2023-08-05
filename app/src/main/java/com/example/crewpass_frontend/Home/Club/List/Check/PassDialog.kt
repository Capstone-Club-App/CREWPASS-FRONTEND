package com.example.crewpass_frontend.Home.Club.List.Check

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.crewpass_frontend.R

class PassDialog (context: Context, Interface: PassDialogInterface) : Dialog(context) {
    private val passDialogInterface : PassDialogInterface = Interface
    private val dlg = Dialog(context)
    private lateinit var btn_send_pass_dlg : AppCompatButton
    private lateinit var edittext_pass : EditText

    fun start(){
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_pass)
        dlg.setCancelable(false)

        btn_send_pass_dlg = dlg.findViewById(R.id.btn_send_pass_dlg)
        edittext_pass = dlg.findViewById(R.id.edittext_pass)

        btn_send_pass_dlg.setOnClickListener {
            dlg.dismiss()
            passDialogInterface.onPassSendButtonClicked(edittext_pass.text.toString())
        }

        dlg.show()
    }



    interface PassDialogInterface {
        fun onPassSendButtonClicked(text : String)
    }
}