package com.android.data.features.loginregister

import com.android.data.features.loginregister.api.LoginRegisterDataSource
import com.android.data.features.loginregister.models.LoginRegisterModel
import com.android.domain.features.loginregister.LoginRegisterRepository

class LoginRegisterRepositoryImpl(private val loginRegisterDataSource: LoginRegisterDataSource) :
    LoginRegisterRepository {
    override suspend fun userRegister(email: String, password: String): Boolean {
        return loginRegisterDataSource.register(LoginRegisterModel(email, password))
    }
}