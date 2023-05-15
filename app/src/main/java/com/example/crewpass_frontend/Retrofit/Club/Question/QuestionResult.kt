package com.example.crewpass_frontend.Retrofit.Club.Question


interface QuestionPostResult {
    fun questionPostSuccess(code : Int)
    fun questionPostFailure(code : Int)
}