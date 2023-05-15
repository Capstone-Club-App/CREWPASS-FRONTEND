package com.example.crewpass_frontend.Retrofit.Club.SignUp


interface SignUpResult {
    // 회원
    fun signUpSuccess(code : Int)
    fun signUpFailure(code : Int)
}

// 동아리 이름 중복 검사
interface CheckDuplicateCrewNameResult{
    fun usableCrewName(code: Int)
    fun unusableCrewName(code: Int)

}

// 동아리 아이디 중복 검사
interface CheckDuplicateCrewIDResult{
    fun usableCrewID(code: Int)
    fun unusableCrewID(code: Int)
}