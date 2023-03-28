package com.example.crewpass_frontend.Login

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.crewpass_frontend.databinding.CustomDialogBinding

class MyCustomDialog(context: Context, myCustomDialogInterface: MyCustomDialogInterface) : Dialog(context) {

    private var mBinding : CustomDialogBinding? = null
    private val binding get() = mBinding!!

    private var myCustomDialogInterface: MyCustomDialogInterface? = null

    // 인터페이스 연결
    init {
        this.myCustomDialogInterface = myCustomDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = CustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명하게
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.btnGotoMain.setOnClickListener {
            this.myCustomDialogInterface?.onbtnGotoMainClicked()
        }

    }
}


interface MyCustomDialogInterface {
    fun onbtnGotoMainClicked()
}