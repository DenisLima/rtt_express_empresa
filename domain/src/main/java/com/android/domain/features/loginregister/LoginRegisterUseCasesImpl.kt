package com.android.domain.features.loginregister

class LoginRegisterUseCasesImpl(private  val loginRegisterRepository: LoginRegisterRepository):LoginRegisterUseCases {
    override suspend fun userRegister(email: String, password: String): Boolean {
        return loginRegisterRepository.userRegister(email
        ,password)
    }
}