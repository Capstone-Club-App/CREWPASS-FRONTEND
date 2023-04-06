package com.example.crewpass_frontend.SignUp

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

class SignUpDialog(context: Context, Interface: SignUpDialogInterface) : Dialog(context) {
    private val signUpDialogInterface : SignUpDialogInterface = Interface
    private val dlg = Dialog(context)
    private lateinit var btn_personal_dlg : AppCompatButton
    private lateinit var btn_club_dlg : AppCompatButton

    fun start(){

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.dialog_signup)
        dlg.setCancelable(false)

        btn_personal_dlg = dlg.findViewById(R.id.btn_personal_dlg)
        btn_club_dlg = dlg.findViewById(R.id.btn_club_dlg)



        btn_personal_dlg.setOnClickListener {
            // 정보 보내주기
            signUpDialogInterface.onPersonalButtonClicked()
        }

        btn_club_dlg.setOnClickListener {
            // 정보 보내주기
            signUpDialogInterface.onClubButtonClicked()
        }



        dlg.show()
    }



    interface SignUpDialogInterface {
        fun onPersonalButtonClicked()
        fun onClubButtonClicked()
    }
}