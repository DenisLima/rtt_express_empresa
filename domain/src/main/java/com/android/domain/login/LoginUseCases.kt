package com.android.domain.login

import com.android.domain.login.ro.LoginResultObject

interface LoginUseCases {
   suspend fun login(email: String, password: String): LoginResultObject
}