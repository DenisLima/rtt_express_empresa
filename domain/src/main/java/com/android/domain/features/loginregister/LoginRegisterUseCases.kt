package com.android.domain.features.loginregister

interface LoginRegisterUseCases {
    suspend fun userRegister(email: String, password: String):Boolean
}