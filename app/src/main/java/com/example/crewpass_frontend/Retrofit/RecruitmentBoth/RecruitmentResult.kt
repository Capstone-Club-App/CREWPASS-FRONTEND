package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

interface RecruitmentGetAllResult {
    fun recruitmentGetAllSuccess(code : Int, data :ArrayList<Recruitment>)
    fun recruitmentGetAllFailure(code : Int)
}

interface RecruitmentGetDetailResult{
    fun recruitmentGetDetailSuccess(code : Int, data :ArrayList<DetailResult>)
    fun recruitmentGetDetailFailure(code : Int)
}