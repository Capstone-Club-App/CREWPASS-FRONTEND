package com.example.crewpass_frontend.MyPage.Personal

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.crewpass_frontend.Login.MyCustomDialogInterface
import com.example.crewpass_frontend.R
import com.example.crewpass_frontend.SignUp.Personal.FindSchool.FindSchoolDialog

class LogoutDialog(context: Context, Interface: LogoutDialogInterface) : Dialog(context) {
    private val logoutDialogInterface : LogoutDialogInterface = Interface
    private val dlg = Dialog(context)
    private lateinit var btn_yes_dlg : AppCompatButton
    private lateinit var btn_no_dlg : AppCompatButton

    fun start(){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_logout)
        dlg.setCancelable(false)

        btn_yes_dlg = dlg.findViewById(R.id.btn_yes_dlg)
        btn_no_dlg = dlg.findViewById(R.id.btn_no_dlg)



        btn_yes_dlg.setOnClickListener {
            dlg.dismiss()
            logoutDialogInterface.onYesButtonClicked()
        }

        btn_no_dlg.setOnClickListener {
            dlg.dismiss()
        }


        dlg.show()
    }



    interface LogoutDialogInterface {
        fun onYesButtonClicked()
    }
}