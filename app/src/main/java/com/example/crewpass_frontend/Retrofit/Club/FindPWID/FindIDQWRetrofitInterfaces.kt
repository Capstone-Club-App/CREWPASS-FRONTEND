package com.example.crewpass_frontend.Retrofit.Club.FindPWID

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FindIDPWRetrofitInterfaces {
    @Multipart
    @POST("/crew/loginId")
    fun findIDByEmailClubName(
        @Part("crewName") crewName : String,
        @Part("email") email : String
    ): Call<FindIdEmailResponse>

    @Multipart
    @POST("/crew/loginId/{crewName}/{email}/{certificateNumb}")
    fun findIDByNumb(
        @Path("crewName") crewName : String,
        @Path("email") email : String,
        @Path("certificateNumb") certificateNumb : Int,
        @Part("inputCertificateNumb") inputCertificateNumb : Int
    ): Call<FindIdNumbResponse>

    @Multipart
    @POST("/crew/password")
    fun findPWByIDEmail(
        @Part("loginId") loginId : String,
        @Part("email") email : String
    ): Call<FindIdEmailResponse>

    @Multipart
    @POST("/crew/password/{loginId}/{email}/{certificateNumb}")
    fun findPWByNumb(
        @Path("loginId") loginId : String,
        @Path("email") email : String,
        @Path("certificateNumb") certificateNumb : Int,
        @Part("inputCertificateNumb") inputCertificateNumb : Int
    ): Call<FindIdNumbResponse>
}