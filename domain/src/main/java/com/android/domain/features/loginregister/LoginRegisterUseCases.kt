package com.android.domain.features.loginregister

import com.android.domain.features.loginregister.ro.LoginRegisterResultObject

interface LoginRegisterUseCases {
    suspend fun userRegister(email: String, password: String): LoginRegisterResultObject
}