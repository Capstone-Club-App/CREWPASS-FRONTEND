package com.example.crewpass_frontend.Retrofit.Personal.Personal

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalService {
    // 동아리 정보 가져오기
    private lateinit var personalGetResult: PersonalGetResult

    fun setPersonalGetResult(personalGetResult: PersonalGetResult) {
        this.personalGetResult = personalGetResult
    }

    fun getPersonal(userId: String) {
        val authService = getRetrofit().create(PersonalGetRetrofitInterfaces::class.java)
        authService.getPersonal(userId).enqueue(object :
            Callback<PersonalResponse> {
            override fun onResponse(call: Call<PersonalResponse>, response: Response<PersonalResponse>) {
                if (response.body() != null) {
                    Log.d("PERSONAL-GET-SUCCESS", response.toString())
                    val resp: PersonalResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            personalGetResult.personalGetSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            personalGetResult.personalGetFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("PERSONAL-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<PersonalResponse>, t: Throwable) {
                Log.d("PERSONAL-GET-FAILURE", t.message.toString())
            }
        })
    }

    // 동아리 정보 수정하기
    private lateinit var personalPutResult: PersonalPutResult

    fun setPersonalPutResult(personalPutResult: PersonalPutResult) {
        this.personalPutResult = personalPutResult
    }

    fun putPersonal(
        userId: String,
        name: String,
        password: String,
        email: String,
        job: String,
        school : String,
        profile: MultipartBody.Part
    ) {
        val authService = getRetrofit().create(PersonalPutRetrofitInterfaces::class.java)
        authService.putPersonal(
            userId,
            name,
            password,
            email,
            job,
            school,
            profile
        ).enqueue(object :
            Callback<PersonalPutResponse> {
            override fun onResponse(
                call: Call<PersonalPutResponse>,
                response: Response<PersonalPutResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("PERSONAL-PUT-SUCCESS", response.toString())
                    val resp: PersonalPutResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            personalPutResult.personalPutSuccess(resp.statusCode)
                        }
                        else -> {
                            personalPutResult.personalPutFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("PERSONAL-PUT-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<PersonalPutResponse>, t: Throwable) {
                Log.d("PERSONAL-PUT-FAILURE", t.message.toString())
            }
        })
    }
}