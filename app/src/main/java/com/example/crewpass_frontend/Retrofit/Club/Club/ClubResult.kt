package com.example.crewpass_frontend.Retrofit.Club.Club

interface ClubGetResult {
    fun clubGetSuccess(code : Int, data :ClubData)
    fun clubGetFailure(code : Int)
}

interface ClubPutResult {
    fun clubPutSuccess(code : Int)
    fun clubPutFailure(code : Int)
}