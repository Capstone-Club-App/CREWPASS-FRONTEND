package com.example.crewpass_frontend.Retrofit.Personal.FindPWID

interface FindIDEmailResult {
    fun findIdEmailSuccess(code: Int, data:ResultEmail)
    fun findIdEmailFailure(code: Int)
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

