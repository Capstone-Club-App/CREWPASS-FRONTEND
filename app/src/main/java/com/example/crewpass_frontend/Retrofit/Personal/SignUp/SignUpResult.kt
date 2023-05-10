package com.example.crewpass_frontend.Retrofit.Personal.SignUp


interface SignUpResult {
    fun signUpSuccess(code : Int)
    fun signUpFailure(code : Int)
}