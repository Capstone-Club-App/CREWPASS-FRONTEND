package com.example.crewpass_frontend.Retrofit.Club.Club

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubService {
    private lateinit var clubGetResult: ClubGetResult

    fun setRecruitmentResult(clubGetResult: ClubGetResult){
        this.clubGetResult = clubGetResult
    }
    fun getClub(){
        val authService = getRetrofit().create(ClubRetrofitInterfaces::class.java)
        authService.getClub().enqueue(object :
            Callback<ClubResponse> {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>,) {
                if(response.body() != null) {
                    Log.d("RECRUIT-GET-SUCCESS",response.toString())
                    val resp: ClubResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            clubGetResult.clubGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            clubGetResult.clubGetFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("RECRUIT-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ClubResponse>, t: Throwable) {
                Log.d("RECRUIT-GET-FAILURE",t.message.toString())
            }
        })
    }
}