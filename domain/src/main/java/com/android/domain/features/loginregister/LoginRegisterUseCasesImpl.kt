package com.android.domain.features.loginregister

import com.android.domain.features.loginregister.ro.LoginRegisterResultObject

class LoginRegisterUseCasesImpl(private val loginRegisterRepository: LoginRegisterRepository) :
    LoginRegisterUseCases {
    override suspend fun userRegister(email: String, password: String): LoginRegisterResultObject {
        return loginRegisterRepository.userRegister(
            email
            , password
        )
    }
}