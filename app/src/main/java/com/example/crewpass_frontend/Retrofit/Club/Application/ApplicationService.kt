package com.example.crewpass_frontend.Retrofit.Club.Application

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicationService {
    // 지원서 작성하기
    private lateinit var applicationGetResult: ApplicationGetResult

    fun setApplicationGetResult(applicationPostResult: ApplicationGetResult) {
        this.applicationGetResult = applicationPostResult
    }

    fun getApplication(
        crewId: Int,
        questionId: Int,
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.getApplication(
            crewId,
            questionId
        ).enqueue(object :
            Callback<ApplicationGetResponse> {
            override fun onResponse(
                call: Call<ApplicationGetResponse>,
                response: Response<ApplicationGetResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("APPLI-GET-SUCCESS", response.toString())
                    val resp: ApplicationGetResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            applicationGetResult.applicationGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            applicationGetResult.applicationGetFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("APPLI-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ApplicationGetResponse>, t: Throwable) {
                Log.d("APPLI-GET-FAILURE", t.message.toString())
            }
        })
    }

    //합불 통보하기
    private lateinit var passNpassResult: PassNpassResult

    fun setPassNpassResult(passNpassResult: PassNpassResult) {
        this.passNpassResult = passNpassResult
    }

    fun postPassNpass(
        crewName: String,
        userId: String,
        msg: String
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.postPassNpass(
            crewName, userId, msg
        ).enqueue(object :
            Callback<PassNpassResponse> {
            override fun onResponse(
                call: Call<PassNpassResponse>,
                response: Response<PassNpassResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("PASSNPASS-SUCCESS", response.toString())
                    val resp: PassNpassResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            passNpassResult.passNpassSuccess(resp.statusCode)
                        }
                        else -> {
                            passNpassResult.passNpassFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("PASSNPASS-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<PassNpassResponse>, t: Throwable) {
                Log.d("PASSNPASS-FAILURE", t.message.toString())
            }
        })
    }
}