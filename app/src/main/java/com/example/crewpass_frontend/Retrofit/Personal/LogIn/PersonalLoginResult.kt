package com.example.crewpass_frontend.Retrofit.Personal.LogIn

interface PersonalLoginResult {
    fun personalLoginSuccess(code : Int, data : Data)
    fun personalLoginUpFailure(code : Int)
}