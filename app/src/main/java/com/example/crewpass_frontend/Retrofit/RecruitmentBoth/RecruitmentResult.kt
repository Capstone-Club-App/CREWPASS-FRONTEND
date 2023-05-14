package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

interface RecruitmentGetAllResult {
    fun recruitmentGetAllSuccess(code : Int, data :ArrayList<Recruitment>)
    fun recruitmentGetAllFailure(code : Int)

    fun recruitmentGetDetailSuccess(code : Int, data :DetailResult)
    fun recruitmentGetDetailFailure(code : Int)
}