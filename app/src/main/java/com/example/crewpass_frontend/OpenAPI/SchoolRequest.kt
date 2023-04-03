package com.example.crewpass_frontend.OpenAPI

import com.google.gson.annotations.SerializedName

data class SchoolRequest(
    @SerializedName("apiKey") val apiKey : String, // 발급받은 key
    @SerializedName("svcType") val svcType : String, // api
    @SerializedName("svcCode") val svcCode : String, // 리스트 : SCHOOL
    @SerializedName("contentType") val contentType : String?, // url에서 .json 생략
    @SerializedName("gubun") val gubun : String, // 학교 분류 (초등, 중등, 고등)
    @SerializedName("region") val region : String?, // 지역
    @SerializedName("sch1") val sch1 : String?, // 학교유형1
    @SerializedName("sch2") val sch2 : String?, // 학교유형 2
    @SerializedName("est") val est : String?, // 설립 유형
    @SerializedName("thisPage") val thisPage : String?, // 현재 페이지
    @SerializedName("perPage") val perPage : String?, // 한페이지당 건수
    @SerializedName("searchSchulNm") val searchSchulNm : String? // 검색어
)
