package com.example.crewpass_frontend.Retrofit.Club.SignUp


interface SignUpResult {
    fun signUpSuccess(code : Int)
    fun signUpFailure(code : Int)
}