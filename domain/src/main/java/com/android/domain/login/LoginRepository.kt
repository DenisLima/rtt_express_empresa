package com.android.domain.login

import com.android.domain.login.ro.LoginResultObject

interface LoginRepository {
   suspend fun login (email: String, password: String): LoginResultObject
}