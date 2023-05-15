package com.example.crewpass_frontend.Retrofit.Club.SignUp

import com.example.crewpass_frontend.Data.Crew
import com.example.crewpass_frontend.Data.SignUp_Club
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignUpRetrofitInterface {
    @Multipart
    @POST("/crew/new/name")
    fun checkDuplicateCrewName(@Part("name") name : String) : Call<SignUpResponse>

    @Multipart
    @POST("/crew/new/loginId")
    fun checkDuplicateCrewID(@Part("loginId") loginId: String) : Call<SignUpResponse>

    @Multipart
    @POST("/crew/new")
    fun signUp (
                @Part("loginId") loginId : String,
                @Part("password") password : String,
                @Part("name") name: String,
                @Part("region1") region1 : String,
                @Part("region2") region2 : String,
                @Part("field1") field1 : String,
                @Part("field2") field2 : String,
                @Part("masterEmail") masterEmail : String,
                @Part("subEmail") subEmail : String,
                @Part profile : MultipartBody.Part
    ): Call<SignUpResponse>
}