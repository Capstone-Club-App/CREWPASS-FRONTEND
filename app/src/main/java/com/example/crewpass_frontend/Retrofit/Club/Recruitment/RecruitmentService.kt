package com.example.crewpass_frontend.Retrofit.Club.Recruitment

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecruitmentService {
    private lateinit var recruitmentGetResult: RecruitmentGetResult

    fun setRecruitmentResult(recruitmentGetResult: RecruitmentGetResult){
        this.recruitmentGetResult = recruitmentGetResult
    }
    fun getRecruitment(crew_id : Int){
        val authService = getRetrofit().create(RecruitmentRetrofitInterfaces::class.java)
        authService.getRecruitment(crew_id).enqueue(object :
            Callback<RecruitmentResponse> {
            override fun onResponse(call: Call<RecruitmentResponse>, response: Response<RecruitmentResponse>,) {
                if(response.body() != null) {
                    Log.d("RECRUIT-GET-SUCCESS",response.toString())
                    val resp: RecruitmentResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            recruitmentGetResult.clubRecruitmentGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            recruitmentGetResult.clubRecruitmentGetFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("RECRUIT-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<RecruitmentResponse>, t: Throwable) {
                Log.d("RECRUIT-GET-FAILURE",t.message.toString())
            }
        })
    }
}