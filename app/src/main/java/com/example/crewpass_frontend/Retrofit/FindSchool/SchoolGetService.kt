package com.example.crewpass_frontend.Retrofit.FindSchool

import android.util.Log
import com.example.crewpass_frontend.OpenAPI.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolGetService {
    private lateinit var schoolGetResult: SchoolGetResult

    fun setChatGetResult(schoolGetResult: SchoolGetResult){
        this.schoolGetResult = schoolGetResult
    }

    fun getSchool(querys : HashMap<String, String>){
        val chatGetService = getRetrofit().create(SchoolGetRetrofitInterfaces::class.java)

        chatGetService.getSchool(querys).enqueue(object : Callback<SchoolGetResponse> {
            override fun onResponse(call: Call<SchoolGetResponse>, response: Response<SchoolGetResponse>,) {
                Log.d("CHAT-GET SUCCESS",response.toString())
                if(response.body() != null) {
                    val resp: SchoolGetResponse = response.body()!!
                    schoolGetResult.getSchoolSuccess(resp.dataSearch!!)
//                    when (resp.code) {
////                        0 -> schoolGetResult.getSchoolSuccess(resp.code, resp.result!!)
////                        else -> schoolGetResult.getSchoolFailure(resp.code, resp.message)
//                    }
                }
                else
                    Log.d("CHAT-GET FAILURE", "NULL")

            }

            override fun onFailure(call: Call<SchoolGetResponse>, t: Throwable) {
                Log.d("CHAT-GET FAILURE",t.message.toString())
            }
        })
    }
}