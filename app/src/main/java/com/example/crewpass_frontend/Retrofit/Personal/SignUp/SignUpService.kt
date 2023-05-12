package com.example.crewpass_frontend.Retrofit.Personal.SignUp

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService {
    private lateinit var signUpResult: SignUpResult

    fun setSignUpResult(signUpResult: SignUpResult){
        this.signUpResult = signUpResult
    }

    fun signUp(name: String,
               loginId : String,
               password : String,
               email : String,
               job : String,
               school : String,
               profile : MultipartBody.Part){
        val authService = getRetrofit().create(SignUpRetrofitInterface::class.java)

        Log.d("profile : ", profile.toString())
        authService.signUp(loginId, password, name, email, job, school,profile).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>,) {
                if(response.body() != null) {
                    Log.d("SIGNUP SUCCESS",response.toString())
                    val resp: SignUpResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> signUpResult.signUpSuccess(resp.statusCode)
                        else -> signUpResult.signUpFailure(resp.statusCode)
                    }
                }
                else
                    Log.d("SIGNUP FAILURE", "NULL")

            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("SIGNUP FAILURE",t.message.toString())
            }
        })
    }
}