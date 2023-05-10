package com.example.crewpass_frontend.Retrofit.Club.LogIn

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart

class LoginService {
    private lateinit var loginResult: LoginResult

    fun setLoginResult(loginResult: LoginResult){
        this.loginResult = loginResult
    }

    fun login(data: Data){

    }
}