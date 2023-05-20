package com.example.crewpass_frontend.Retrofit.Club.Question

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionService {
    private lateinit var questionPostResult: QuestionPostResult

    fun setQuestionPostResult(questionPostResult: QuestionPostResult) {
        this.questionPostResult = questionPostResult
    }

    fun postQuestion(
        recruitment_id: Int,
        question1: String, question2: String, question3: String,
        question4: String, question5: String?, question6: String?, question7: String?,
        question1Limit: Int, question2Limit: Int, question3Limit: Int,
        question4Limit: Int, question5Limit: Int?, question6Limit: Int?,
        question7Limit: Int?
    ) {
        val authService = getRetrofit().create(QuestionRetrofitInterfaces::class.java)
        authService.postQuestion(
            recruitment_id,
            question1,
            question2,
            question3,
            question4,
            question5,
            question6,
            question7,
            question1Limit,
            question2Limit,
            question3Limit,
            question4Limit,
            question5Limit,
            question6Limit,
            question7Limit
        ).enqueue(object :
            Callback<QuestionPostResponse> {
            override fun onResponse(
                call: Call<QuestionPostResponse>,
                response: Response<QuestionPostResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("QUESTION-POST-SUCCESS", response.toString())
                    val resp: QuestionPostResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            questionPostResult.questionPostSuccess(resp.statusCode)
                        }
                        else -> {
                            questionPostResult.questionPostFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("QUESTION-POST-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<QuestionPostResponse>, t: Throwable) {
                Log.d("QUESTION-POST-FAILURE", t.message.toString())
            }
        })
    }
}