package com.example.crewpass_frontend.Retrofit.Personal.Scrap

import android.util.Log
import com.example.crewpass_frontend.Login.logined_id
import com.example.crewpass_frontend.Retrofit.RecruitmentBoth.RecruitmentGetAllResult
import com.example.crewpass_frontend.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScrapService {

    // 스크랩 등록
    private lateinit var scrapPostResult: ScrapPostResult

    fun setScrapPostResult(scrapPostResult: ScrapPostResult){
        this.scrapPostResult = scrapPostResult
    }

    fun postScrap(recruitmentId : Int){
        val authService = getRetrofit().create(ScrapRetrofitInterfaces::class.java)
        authService.postScrap(recruitmentId, logined_id).enqueue(object :
            Callback<ScrapResponse> {
            override fun onResponse(call: Call<ScrapResponse>, response: Response<ScrapResponse>,) {
                if(response.body() != null) {
                    Log.d("SCRAP-POST-SUCCESS",response.toString())
                    val resp: ScrapResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            scrapPostResult.scrapPostSuccess(resp.statusCode, resp.scrap_id)
                        }
                        else -> {
                            scrapPostResult.scrapPostFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("SCRAP-POST-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ScrapResponse>, t: Throwable) {
                Log.d("SCRAP-POST-FAILURE",t.message.toString())
            }
        })
    }

    // 스크랩 취소
    private lateinit var scrapDeleteResult: ScrapDeleteResult
    fun setScrapDeleteResult(scrapDeleteResult: ScrapDeleteResult){
        this.scrapDeleteResult = scrapDeleteResult
    }

    fun deleteScrap(scrapId : Int){
        val authService = getRetrofit().create(ScrapRetrofitInterfaces::class.java)
        authService.deleteScrap(scrapId, logined_id).enqueue(object :
            Callback<ScrapDeleteResponse> {
            override fun onResponse(call: Call<ScrapDeleteResponse>, response: Response<ScrapDeleteResponse>,) {
                if(response.body() != null) {
                    Log.d("SCRAP-DELETE-SUCCESS",response.toString())
                    val resp: ScrapDeleteResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            scrapDeleteResult.scrapDeleteSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            scrapDeleteResult.scrapDeleteFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("SCRAP-DELETE-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ScrapDeleteResponse>, t: Throwable) {
                Log.d("SCRAP-DELETE-FAILURE",t.message.toString())
            }
        })
    }

    // 스크랩 전체 가져오기
    private lateinit var scrapGetAllResult: ScrapGetAllResult
    fun setScrapGetAllResult(scrapGetAllResult: ScrapGetAllResult){
        this.scrapGetAllResult = scrapGetAllResult
    }

    fun getScrap(userId : Int){
        val authService = getRetrofit().create(ScrapRetrofitInterfaces::class.java)
        authService.getScrap(userId).enqueue(object :
            Callback<ScrapGetAllResponse> {
            override fun onResponse(call: Call<ScrapGetAllResponse>, response: Response<ScrapGetAllResponse>,) {
                if(response.body() != null) {
                    Log.d("SCRAP-GET-SUCCESS",response.toString())
                    val resp: ScrapGetAllResponse = response.body()!!
                    when (resp.statusCode) {
                        200 -> {
                            scrapGetAllResult.scrapGetAllSuccess(resp.statusCode, resp.data)
                        }
                        else -> {
                            scrapGetAllResult.scrapGetAllFailure(resp.statusCode)
                        }
                    }
                }
                else
                    Log.d("SCRAP-GET-FAILURE", "NULL")

            }

            override fun onFailure(call: Call<ScrapGetAllResponse>, t: Throwable) {
                Log.d("SCRAP-GET-FAILURE",t.message.toString())
            }
        })
    }
}