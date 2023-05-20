package com.example.crewpass_frontend.Retrofit.Personal.Personal

interface PersonalGetResult {
    fun personalGetSuccess(code : Int, data :PersonalData)
    fun personalGetFailure(code : Int)
}

interface PersonalPutResult {
    fun personalPutSuccess(code : Int)
    fun personalPutFailure(code : Int)
}