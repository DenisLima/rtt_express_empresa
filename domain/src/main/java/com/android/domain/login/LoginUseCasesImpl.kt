package com.android.domain.login

import com.android.domain.login.ro.LoginResultObject

class LoginUseCasesImpl(private val loginRepository: LoginRepository) : LoginUseCases {

    override suspend fun login(email: String, password: String): LoginResultObject {
        return loginRepository.login(
            email
            , password
        )
    }
}