package com.example.crewpass_frontend.Retrofit.Club.Recruitment

interface RecruitmentGetResult {
    fun clubRecruitmentGetSuccess(code : Int, data : ArrayList<RecruitmentData>)
    fun clubRecruitmentGetFailure(code : Int)
}

interface RecruitmentPostResult {
    fun clubRecruitmentPostSuccess(code : Int, data : PostResult)
    fun clubRecruitmentPostFailure(code : Int)
}