package com.example.crewpass_frontend.Retrofit.FindSchool

interface SchoolGetResult {
    fun getSchoolSuccess(result: Content)
    fun getSchoolFailure(code : Int, message : String)
}