package com.example.crewpass_frontend.Retrofit.Club.Application

interface ApplicationGetResult {
    fun applicationGetSuccess(code: Int, data: ArrayList<ApplicationData>)
    fun applicationGetFailure(code: Int)
}

interface PassNpassResult {
    fun passNpassSuccess(code: Int)
    fun passNpassFailure(code: Int)
}