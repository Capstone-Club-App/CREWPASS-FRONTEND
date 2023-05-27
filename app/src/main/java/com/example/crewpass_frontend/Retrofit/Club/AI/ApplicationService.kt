package com.example.crewpass_frontend.Retrofit.Club.AI

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalyzeApplicationService {
    // 지원서 작성하기
    private lateinit var analyzeApplicationResult: AnalyzeApplicationResult

    fun setAnalyzeApplicationResult(analyzeApplicationResult: AnalyzeApplicationResult) {
        this.analyzeApplicationResult = analyzeApplicationResult
    }

    fun analyzeApplication(
        applicationId: Int
    ) {
        val authService = getRetrofit().create(AnalyzeApplicationRetrofitInterfaces::class.java)
        authService.analyzeApplication(
            applicationId
        ).enqueue(object :
            Callback<AnalyzeApplicationResponse> {
            override fun onResponse(
                call: Call<AnalyzeApplicationResponse>,
                response: Response<AnalyzeApplicationResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("ANALYZE-SUCCESS", response.toString())
                    val resp: AnalyzeApplicationResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            analyzeApplicationResult.analyzeApplicationSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            analyzeApplicationResult.analyzeApplicationFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("ANALYZE-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<AnalyzeApplicationResponse>, t: Throwable) {
                Log.d("ANALYZE-ONFAILURE", t.message.toString())
            }
        })
    }

}