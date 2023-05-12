package com.example.crewpass_frontend.Retrofit.Club.LogIn

interface ClubLoginResult {
    fun clubLoginSuccess(code : Int, data : ClubData)
    fun clubLoginUpFailure(code : Int)
}