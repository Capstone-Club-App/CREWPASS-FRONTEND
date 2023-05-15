package com.example.crewpass_frontend.Retrofit.Personal.SignUp


interface SignUpResult {
    fun signUpSuccess(code : Int)
    fun signUpFailure(code : Int)
}

// 회원 이름 중복 검사
interface CheckDuplicateEmailResult{
    fun usableEmail(code: Int)
    fun unusableEmail(code: Int)

}

// 회원 아이디 중복 검사
interface CheckDuplicateIDResult{
    fun usableID(code: Int)
    fun unusableID(code: Int)
}