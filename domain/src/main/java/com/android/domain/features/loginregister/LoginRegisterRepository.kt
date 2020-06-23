package com.android.domain.features.loginregister

interface LoginRegisterRepository {
    suspend fun userRegister(email: String, password: String):
}