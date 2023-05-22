package com.example.crewpass_frontend.Retrofit.Personal.Application

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Part
import retrofit2.http.Path

class ApplicationService {
    // 지원서 작성하기
    private lateinit var applicationPostResult: ApplicationPostResult

    fun setApplicationPostResult(applicationPostResult: ApplicationPostResult) {
        this.applicationPostResult = applicationPostResult
    }

    fun postApplication(
        userId: Int,
        questionId: Int,
        answer1: String,
        answer2: String,
        answer3: String,
        answer4: String?,
        answer5: String?,
        answer6: String?,
        answer7: String?,
        answer1Count: Int,
        answer2Count: Int,
        answer3Count: Int,
        answer4Count: Int?,
        answer5Count: Int?,
        answer6Count: Int?,
        answer7Count: Int?,
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.postApplication(
            userId,
            questionId,
            answer1,
            answer2,
            answer3,
            answer4,
            answer5,
            answer6,
            answer7,
            answer1Count,
            answer2Count,
            answer3Count,
            answer4Count,
            answer5Count,
            answer6Count,
            answer7Count
        ).enqueue(object :
            Callback<ApplicationPostResponse> {
            override fun onResponse(
                call: Call<ApplicationPostResponse>,
                response: Response<ApplicationPostResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("APPLI-POST-SUCCESS", response.toString())
                    val resp: ApplicationPostResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            applicationPostResult.applicationPostSuccess(resp.statusCode)
                        }
                        else -> {
                            applicationPostResult.applicationPostFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("APPLI-POST-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ApplicationPostResponse>, t: Throwable) {
                Log.d("APPLI-POST-FAILURE", t.message.toString())
            }
        })
    }


    // 지원서 작성하기
    private lateinit var questionGetResult: QuestionGetResult

    fun setQuestionGetResult(questionGetResult: QuestionGetResult) {
        this.questionGetResult = questionGetResult
    }

    fun getQuestion(
        questionId: Int,
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.getQuestion(
            questionId,
        ).enqueue(object :
            Callback<QuestionGetResponse> {
            override fun onResponse(
                call: Call<QuestionGetResponse>,
                response: Response<QuestionGetResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("Q-GET-SUCCESS", response.toString())
                    val resp: QuestionGetResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            questionGetResult.questionGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            questionGetResult.questionGetFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("Q-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<QuestionGetResponse>, t: Throwable) {
                Log.d("Q-GET-FAILURE", t.message.toString())
            }
        })
    }

    // 지원서 상세 가져오기
    private lateinit var applicationGetResult: ApplicationGetResult

    fun setApplicationGetResult(applicationGetResult: ApplicationGetResult) {
        this.applicationGetResult = applicationGetResult
    }

    fun getApplication(
        applicationId: Int,
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.getApplication(
            applicationId,
        ).enqueue(object :
            Callback<ApplicationGetResponse> {
            override fun onResponse(
                call: Call<ApplicationGetResponse>,
                response: Response<ApplicationGetResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("APPI-GET-SUCCESS", response.toString())
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
                    Log.d("APPI-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ApplicationGetResponse>, t: Throwable) {
                Log.d("APPI-GET-FAILURE", t.message.toString())
            }
        })
    }

    // 지원서 상세 가져오기
    private lateinit var applicationGetListResult: ApplicationGetListResult

    fun setApplicationGetListResult(applicationGetListResult: ApplicationGetListResult) {
        this.applicationGetListResult = applicationGetListResult
    }

    fun getApplicationList(
        userId: Int,
    ) {
        val authService = getRetrofit().create(ApplicationRetrofitInterfaces::class.java)
        authService.getApplicationList(
            userId,
        ).enqueue(object :
            Callback<ApplicationGetListResponse> {
            override fun onResponse(
                call: Call<ApplicationGetListResponse>,
                response: Response<ApplicationGetListResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("APPI-GET-SUCCESS", response.toString())
                    val resp: ApplicationGetListResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            applicationGetListResult.applicationGetListSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            applicationGetListResult.applicationGetListFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("APPI-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ApplicationGetListResponse>, t: Throwable) {
                Log.d("APPI-GET-FAILURE", t.message.toString())
            }
        })
    }

}