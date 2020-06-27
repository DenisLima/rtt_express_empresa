package com.android.domain.features.loginregister

import com.android.domain.features.loginregister.ro.LoginRegisterResultObject

interface LoginRegisterRepository {
    suspend fun userRegister(email: String, password: String, fullName: String): LoginRegisterResultObject
}