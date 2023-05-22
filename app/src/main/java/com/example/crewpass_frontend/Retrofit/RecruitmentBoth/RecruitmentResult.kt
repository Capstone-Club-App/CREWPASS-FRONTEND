package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

interface RecruitmentGetAllResult {
    fun recruitmentGetAllSuccess(code : Int, data :ArrayList<Recruitment>)
    fun recruitmentGetAllFailure(code : Int)
}

interface RecruitmentGetDeadlineResult {
    fun recruitmentGetDeadlineSuccess(code : Int, data :ArrayList<Recruitment>)
    fun recruitmentGetDeadlineFailure(code : Int)
}

interface RecruitmentGetDetailResult{
    fun recruitmentGetDetailSuccess(code : Int, data :DetailResult)
    fun recruitmentGetDetailFailure(code : Int)
}