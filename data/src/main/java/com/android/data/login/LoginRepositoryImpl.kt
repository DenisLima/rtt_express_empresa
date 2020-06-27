package com.android.data.login

import com.android.data.login.api.LoginDataSource
import com.android.data.login.models.LoginModel
import com.android.domain.login.LoginRepository
import com.android.domain.login.ro.LoginResultObject

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {
    override suspend fun login(email: String, password: String): LoginResultObject {

        var loginResultObject = LoginResultObject()

        loginDataSource.login(
            LoginModel(email, password)
        ).let {
            loginResultObject!!.email = it.email
            loginResultObject!!.password = it.password

        }

        return loginResultObject!!
    }
}