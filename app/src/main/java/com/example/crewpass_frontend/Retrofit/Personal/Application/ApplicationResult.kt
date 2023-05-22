package com.example.crewpass_frontend.Retrofit.Personal.Application

interface ApplicationPostResult {
    fun applicationPostSuccess(code: Int)
    fun applicationPostFailure(code: Int)
}

interface QuestionGetResult {
    fun questionGetSuccess(code: Int, data: QuestionData)
    fun questionGetFailure(code: Int)
}

interface ApplicationGetResult {
    fun applicationGetSuccess(code: Int, data: ArrayList<ApplicationData>)
    fun applicationGetFailure(code: Int)
}

interface ApplicationGetListResult {
    fun applicationGetListSuccess(code: Int, data: ArrayList<Application>)
    fun applicationGetListFailure(code: Int)
}