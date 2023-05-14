package com.example.crewpass_frontend.Retrofit.RecruitmentBoth

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecruitmentAllService {
    private lateinit var recruitmentGetAllResult: RecruitmentGetAllResult

    fun setRecruitmentGetAllResult(recruitmentGetAllResult: RecruitmentGetAllResult){
        this.recruitmentGetAllResult = recruitmentGetAllResult
    }
    fun getRecruitmentAll(field : String){
        val authService = getRetrofit().create(RecruitmentRetrofitInterfaces::class.java)
        authService.getRecruitmentAll(field).enqueue(object :
            Callback<RecruitmentResponse> {
            override fun onResponse(call: Call<RecruitmentResponse>, response: Response<RecruitmentResponse>,) {
                if(response.body() != null) {
                    Log.d("RECRUIT-GET-SUCCESS",response.toString())
                    val resp: RecruitmentResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            recruitmentGetAllResult.recruitmentGetAllSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            recruitmentGetAllResult.recruitmentGetAllFailure(resp.statusCode)
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

    fun getRecruitmentDetail(recruitment_id : Int){
        val authService = getRetrofit().create(RecruitmentRetrofitInterfaces::class.java)
        authService.getRecruitmenDetail(recruitment_id).enqueue(object :
            Callback<RecruitmentDetailResponse> {
            override fun onResponse(call: Call<RecruitmentDetailResponse>, response: Response<RecruitmentDetailResponse>,) {
                if(response.body() != null) {
                    Log.d("RECRUIT-GET-SUCCESS",response.toString())
                    val resp: RecruitmentDetailResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            recruitmentGetAllResult.recruitmentGetAllSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            recruitmentGetAllResult.recruitmentGetAllFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("RECRUIT-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<RecruitmentDetailResponse>, t: Throwable) {
                Log.d("RECRUIT-GET-FAILURE",t.message.toString())
            }
        })
    }
}