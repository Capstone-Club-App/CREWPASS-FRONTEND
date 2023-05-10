package com.example.crewpass_frontend.Retrofit.Club.LogIn

interface LoginResult {
    fun loginSuccess(code : Int)
    fun loginUpFailure(code : Int)
}