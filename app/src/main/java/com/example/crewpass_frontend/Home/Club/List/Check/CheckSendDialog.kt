package com.example.crewpass_frontend.Home.Club.List.Check

import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.crewpass_frontend.R

class CheckSendDialog(context: Context, Interface: CheckSendDialogInterface) : Dialog(context) {
    private val checkSendDialogInterface : CheckSendDialogInterface = Interface
    private val dlg = Dialog(context)
    private lateinit var btn_send_ok_dlg : AppCompatButton
    private lateinit var btn_send_cancel_dlg : AppCompatButton

    fun start(isPass : Boolean, text : String){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_check_send)
        dlg.setCancelable(false)

        btn_send_ok_dlg = dlg.findViewById(R.id.btn_send_ok_dlg)
        btn_send_cancel_dlg = dlg.findViewById(R.id.btn_send_cancel_dlg)

        btn_send_ok_dlg.setOnClickListener {
            dlg.dismiss()
            checkSendDialogInterface.onOKButtonClicked(isPass, text)
        }

        btn_send_cancel_dlg.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
    interface CheckSendDialogInterface {
        fun onOKButtonClicked(isPass : Boolean, msg : String)
    }
}