package com.example.crewpass_frontend.Retrofit.FindSchool

interface SchoolGetResult {
    fun getSchoolSuccess(result: DataSearch)
    fun getSchoolFailure(code : Int, message : String)
}