package com.example.crewpass_frontend.Retrofit.Club.FindPWID

interface FindIDEmailClubNameResult {
    fun findIdEmailClubNameSuccess(code: Int, data:ResultEmail)
    fun findIdEmailClubNameFailure(code: Int)
}

interface FindIDNumbResult {
    fun findIdNumbSuccess(code: Int, data:ResultNumb)
    fun findIdNumbFailure(code: Int)
}

interface FindPWEmailResult {
    fun findIdPWSuccess(code: Int, data:ResultEmail)
    fun findIdPWFailure(code: Int)
}

interface FindPWNumbResult {
    fun findNumbPWSuccess(code: Int, data:ResultNumb)
    fun findNumbPWFailure(code: Int)
}

