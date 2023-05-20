package com.example.crewpass_frontend.Retrofit.Personal.Application

interface ApplicationPostResult {
    fun applicationPostSuccess(code: Int)
    fun applicationPostFailure(code: Int)
}