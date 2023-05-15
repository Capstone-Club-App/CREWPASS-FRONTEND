package com.example.crewpass_frontend.Retrofit.Club.Recruitment

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import okhttp3.MultipartBody
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

    // 모집글 등록하기
    private lateinit var recruitmentPostResult: RecruitmentPostResult

    fun setRecruitmentResult(recruitmentPostResult: RecruitmentPostResult){
        this.recruitmentPostResult = recruitmentPostResult
    }

    fun postRecruitment(crew_id: Int, isDeleted: Int, title: String, deadline: String, content : String, image: MultipartBody.Part){
        val authService = getRetrofit().create(RecruitmentRetrofitInterfaces::class.java)
        authService.postRecruitment(title, isDeleted, deadline, content, image, crew_id).enqueue(object :
            Callback<RecruitmentPostResponse> {
            override fun onResponse(call: Call<RecruitmentPostResponse>, response: Response<RecruitmentPostResponse>,) {
                if(response.body() != null) {
                    Log.d("RECRUIT-POST-SUCCESS",response.toString())
                    val resp: RecruitmentPostResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            recruitmentPostResult.clubRecruitmentPostSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            recruitmentPostResult.clubRecruitmentPostFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("RECRUIT-POST-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<RecruitmentPostResponse>, t: Throwable) {
                Log.d("RECRUIT-GET-FAILURE",t.message.toString())
            }
        })
    }
}