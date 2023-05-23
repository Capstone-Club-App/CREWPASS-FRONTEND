package com.example.crewpass_frontend.Retrofit.Personal.FindPWID

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FindIDPWRetrofitInterfaces {
    @Multipart
    @POST("/user/loginId")
    fun findIDByEmail(
        @Part("email") email : String
    ): Call<FindIdEmailResponse>

    @Multipart
    @POST("/user/loginId/{email}/{certificateNumb}")
    fun findIDByNumb(
        @Path("email") email : String,
        @Path("certificateNumb") certificateNumb : Int,
        @Part("inputCertificateNumb") inputCertificateNumb : Int
    ): Call<FindIdNumbResponse>

    @Multipart
    @POST("/user/password")
    fun findPWByIDEmail(
        @Part("loginId") loginId : String,
        @Part("email") email : String
    ): Call<FindIdEmailResponse>

    @Multipart
    @POST("/user/password/{loginId}/{email}/{certificateNumb}")
    fun findPWByNumb(
        @Path("loginId") loginId : String,
        @Path("email") email : String,
        @Path("certificateNumb") certificateNumb : Int,
        @Part("inputCertificateNumb") inputCertificateNumb : Int
    ): Call<FindIdNumbResponse>
}