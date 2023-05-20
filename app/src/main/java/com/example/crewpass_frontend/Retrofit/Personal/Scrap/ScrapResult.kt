package com.example.crewpass_frontend.Retrofit.Personal.Scrap


interface ScrapPostResult {
    fun scrapPostSuccess(code : Int, scrap_id :Int)
    fun scrapPostFailure(code : Int)
}

interface ScrapDeleteResult {
    fun scrapDeleteSuccess(code : Int, data :Any?)
    fun scrapDeleteFailure(code : Int)
}

interface ScrapGetAllResult {
    fun scrapGetAllSuccess(code : Int, data :ArrayList<getResult>)
    fun scrapGetAllFailure(code : Int)
}