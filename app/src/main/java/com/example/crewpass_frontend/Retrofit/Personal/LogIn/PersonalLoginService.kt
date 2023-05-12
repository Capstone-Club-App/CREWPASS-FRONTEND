package com.example.crewpass_frontend.Retrofit.Personal.LogIn

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalLoginService {
    private lateinit var personalLoginResult: PersonalLoginResult

    fun setLoginResult(personalLoginResult: PersonalLoginResult){
        this.personalLoginResult = personalLoginResult
    }
    fun login(loginId : String,
              password : String){
        val authService = getRetrofit().create(PersonalLoginRetrofitInterfaces::class.java)
        authService.login(loginId, password).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>,) {
                if(response.body() != null) {
                    Log.d("LOGIN-SUCCESS",response.toString())
                    val resp: LoginResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            personalLoginResult.personalLoginSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            personalLoginResult.personalLoginUpFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("LOGIN-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LOGIN-FAILURE",t.message.toString())
            }
        })
    }
}