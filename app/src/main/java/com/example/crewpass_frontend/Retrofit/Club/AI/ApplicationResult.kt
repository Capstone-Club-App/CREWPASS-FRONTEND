package com.example.crewpass_frontend.Retrofit.Club.AI

interface AnalyzeApplicationResult {
    fun analyzeApplicationSuccess(code: Int, data: AnalyzeResult)
    fun analyzeApplicationFailure(code: Int)
}

