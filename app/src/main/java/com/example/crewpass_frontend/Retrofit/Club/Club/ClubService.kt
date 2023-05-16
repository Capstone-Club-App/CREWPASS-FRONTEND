package com.example.crewpass_frontend.Retrofit.Club.Club

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubService {
    // 동아리 정보 가져오기
    private lateinit var clubGetResult: ClubGetResult

    fun setClubGetResult(clubGetResult: ClubGetResult) {
        this.clubGetResult = clubGetResult
    }

    fun getClub(crewId: Int) {
        val authService = getRetrofit().create(ClubGetRetrofitInterfaces::class.java)
        authService.getClub(crewId).enqueue(object :
            Callback<ClubResponse> {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>) {
                if (response.body() != null) {
                    Log.d("CLUB-GET-SUCCESS", response.toString())
                    val resp: ClubResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            clubGetResult.clubGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            clubGetResult.clubGetFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("CLUB-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ClubResponse>, t: Throwable) {
                Log.d("CLUB-GET-FAILURE", t.message.toString())
            }
        })
    }

    // 동아리 정보 수정하기
    private lateinit var clubPutResult: ClubPutResult

    fun setClubPutResult(clubPutResult: ClubPutResult) {
        this.clubPutResult = clubPutResult
    }

    fun putClub(
        crewId: Int,
        name: String,
        password: String,
        region1: String,
        region2: String,
        field1: String,
        field2: String,
        masterEmail: String,
        subEmail: String,
        profile: MultipartBody.Part
    ) {
        val authService = getRetrofit().create(ClubPutRetrofitInterfaces::class.java)
        authService.putClub(
            crewId, name,
            password,
            region1, region2,
            field1, field2,
            masterEmail,
            subEmail,
            profile
        ).enqueue(object :
            Callback<ClubPutResponse> {
            override fun onResponse(
                call: Call<ClubPutResponse>,
                response: Response<ClubPutResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("CLUB-PUT-SUCCESS", response.toString())
                    val resp: ClubPutResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            clubPutResult.clubPutSuccess(resp.statusCode)
                        }
                        else -> {
                            clubPutResult.clubPutFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("CLUB-PUT-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ClubPutResponse>, t: Throwable) {
                Log.d("CLUB-PUT-FAILURE", t.message.toString())
            }
        })
    }
}