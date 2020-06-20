package com.android.domain.login

import com.android.domain.login.models.User

interface LoginRepository {
   suspend fun login (email: String, password: String): User
}