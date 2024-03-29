package com.android.data.features.loginregister

import com.android.data.features.loginregister.api.LoginRegisterDataSource
import com.android.data.features.loginregister.models.LoginRegisterModel
import com.android.data.features.loginregister.ro.LoginRegisterResponseObject
import com.android.domain.features.loginregister.LoginRegisterRepository
import com.android.domain.features.loginregister.ro.LoginRegisterResultObject

class LoginRegisterRepositoryImpl(private val loginRegisterDataSource: LoginRegisterDataSource) :
    LoginRegisterRepository {
    override suspend fun userRegister(email: String, password: String, fullName: String): LoginRegisterResultObject {

        var loginRegisterResultObject = LoginRegisterResultObject()

        loginRegisterDataSource.register(LoginRegisterModel(email, password, fullName)).let {
            loginRegisterResultObject!!.id = it.id
            loginRegisterResultObject!!.email = it.email
            loginRegisterResultObject!!.fullName = it.fullName
            loginRegisterResultObject!!.password = it.password
            loginRegisterResultObject!!.isAcceptedTerm = it.isAcceptedTerm
        }

        return loginRegisterResultObject!!
    }
}