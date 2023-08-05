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
        questionId: Int
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.getApplication(
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
                Log.d("APPLI-GET-ONFAILURE", t.message.toString())
            }
        })
    }

    //합불 통보하기
    private lateinit var passNpassResult: PassNpassResult

    fun setPassNpassResult(passNpassResult: PassNpassResult) {
        this.passNpassResult = passNpassResult
    }

    fun postPass(
        recruitmentId : Int,
        crewName: String,
        user_list: ArrayList<Int>,
        msg: String
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.postPass(
            recruitmentId, crewName, user_list, msg
        ).enqueue(object :
            Callback<PassNpassResponse> {
            override fun onResponse(
                call: Call<PassNpassResponse>,
                response: Response<PassNpassResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("PASSPASS-SUCCESS", response.toString())
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
                    Log.d("PASSPASS-FAILURE", "NULL")
            }
            override fun onFailure(call: Call<PassNpassResponse>, t: Throwable) {
                Log.d("PASSPASS-FAILURE", t.message.toString())
            }
        })
    }

    fun postNpass(
        recruitmentId : Int,
        crewName: String,
        user_list: ArrayList<Int>,
        msg: String
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.postNpass(
            recruitmentId, crewName, user_list, msg
        ).enqueue(object :
            Callback<PassNpassResponse> {
            override fun onResponse(
                call: Call<PassNpassResponse>,
                response: Response<PassNpassResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("NPASS-SUCCESS", response.toString())
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
                    Log.d("NPASS-FAILURE", "NULL")
            }
            override fun onFailure(call: Call<PassNpassResponse>, t: Throwable) {
                Log.d("NPASS-FAILURE", t.message.toString())
            }
        })
    }
}