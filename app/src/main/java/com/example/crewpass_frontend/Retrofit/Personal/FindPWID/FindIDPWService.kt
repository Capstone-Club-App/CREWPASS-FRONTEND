package com.example.crewpass_frontend.Retrofit.Personal.FindPWID

import android.util.Log
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import kotlin.math.log

class FindIDPWService {

    // 아이디 이메일 인증
    private lateinit var findIDEmailResult: FindIDEmailResult

    fun setFindIDEmailResult(findIDEmailResult: FindIDEmailResult) {
        this.findIDEmailResult = findIDEmailResult
    }

    fun findIDByEmail(
        email: String,
    ) {
        val authService = getRetrofit().create(FindIDPWRetrofitInterfaces::class.java)
        authService.findIDByEmail(
            email,
        ).enqueue(object :
            Callback<FindIdEmailResponse> {
            override fun onResponse(
                call: Call<FindIdEmailResponse>,
                response: Response<FindIdEmailResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("FIND-E-SUCCESS", response.toString())
                    val resp: FindIdEmailResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            findIDEmailResult.findIdEmailSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            findIDEmailResult.findIdEmailFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("FIND-E-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<FindIdEmailResponse>, t: Throwable) {
                Log.d("FIND-E-FAILURE", t.message.toString())
            }
        })
    }

    // 아이디 인증번호 입력 인증
    private lateinit var findIDNumbResult: FindIDNumbResult

    fun setFindIDNumbResult(findIDNumbResult: FindIDNumbResult) {
        this.findIDNumbResult = findIDNumbResult
    }

    fun findIDByNumb(
        email: String, certificateNumb : Int, inputCertificateNumb : Int
    ) {
        Log.d("email:$email, certi:$certificateNumb, input: $inputCertificateNumb", "")
        val authService = getRetrofit().create(FindIDPWRetrofitInterfaces::class.java)
        authService.findIDByNumb(
            email, certificateNumb, inputCertificateNumb
        ).enqueue(object :
            Callback<FindIdNumbResponse> {
            override fun onResponse(
                call: Call<FindIdNumbResponse>,
                response: Response<FindIdNumbResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("FIND-N-SUCCESS", response.toString())
                    val resp: FindIdNumbResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            findIDNumbResult.findIdNumbSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            findIDNumbResult.findIdNumbFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("FIND-N-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<FindIdNumbResponse>, t: Throwable) {
                Log.d("FIND-N-FAILURE", t.message.toString())
            }
        })
    }


    // 아이디 이메일 인증
    private lateinit var findPWEmailResult: FindPWEmailResult

    fun setFindPWEmailResult(findPWEmailResult: FindPWEmailResult) {
        this.findPWEmailResult = findPWEmailResult
    }

    fun findPWByEmail(
        email: String, loginId: String
    ) {
        val authService = getRetrofit().create(FindIDPWRetrofitInterfaces::class.java)
        authService.findPWByIDEmail(
            loginId, email
        ).enqueue(object :
            Callback<FindIdEmailResponse> {
            override fun onResponse(
                call: Call<FindIdEmailResponse>,
                response: Response<FindIdEmailResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("FIND-E-SUCCESS", response.toString())
                    val resp: FindIdEmailResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            findPWEmailResult.findIdPWSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            findPWEmailResult.findIdPWFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("FIND-E-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<FindIdEmailResponse>, t: Throwable) {
                Log.d("FIND-E-FAILURE", t.message.toString())
            }
        })
    }


    // 아이디 인증번호 입력 인증
    private lateinit var findPWNumbResult: FindPWNumbResult

    fun setFindPWNumbResult(findPWNumbResult: FindPWNumbResult) {
        this.findPWNumbResult = findPWNumbResult
    }

    fun findPWByNumb(
        loginId : String,
        email : String,
        certificateNumb : Int,
        inputCertificateNumb : Int
    ) {
        val authService = getRetrofit().create(FindIDPWRetrofitInterfaces::class.java)
        authService.findPWByNumb(
            loginId, email, certificateNumb, inputCertificateNumb
        ).enqueue(object :
            Callback<FindIdNumbResponse> {
            override fun onResponse(
                call: Call<FindIdNumbResponse>,
                response: Response<FindIdNumbResponse>,
            ) {
                if (response.body() != null) {
                    Log.d("FIND-N-SUCCESS", response.toString())
                    val resp: FindIdNumbResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            findPWNumbResult.findNumbPWSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            findPWNumbResult.findNumbPWFailure(resp.statusCode)
                        }
                    }
                } else
                    Log.d("FIND-N-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<FindIdNumbResponse>, t: Throwable) {
                Log.d("FIND-N-FAILURE", t.message.toString())
            }
        })
    }
}