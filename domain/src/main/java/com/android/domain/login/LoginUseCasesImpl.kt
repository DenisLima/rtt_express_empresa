package com.android.domain.login

import com.android.domain.login.models.User

class LoginUseCasesImpl(private val loginRepository: LoginRepository) : LoginUseCases {
    override suspend fun login(email: String, password: String): User {
        return loginRepository.login(
            email
            , password
        )
    }
}