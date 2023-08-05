package com.example.crewpass_frontend.Home.Club.List.Check

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.example.crewpass_frontend.R

class NPassDialog (context: Context, Interface: NPassDialogInterface) : Dialog(context) {
    private val passDialogInterface : NPassDialogInterface = Interface
    private val dlg = Dialog(context)
    private lateinit var btn_send_npass_dlg : AppCompatButton
    private lateinit var edittext_npass : EditText

    fun start(){
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_npass)
        dlg.setCancelable(false)

        btn_send_npass_dlg = dlg.findViewById(R.id.btn_send_npass_dlg)
        edittext_npass = dlg.findViewById(R.id.edittext_npass)

        btn_send_npass_dlg.setOnClickListener {
            dlg.dismiss()
            passDialogInterface.onNPassSendButtonClicked(edittext_npass.text.toString())
        }

        dlg.show()
    }



    interface NPassDialogInterface {
        fun onNPassSendButtonClicked(text : String)
    }
}