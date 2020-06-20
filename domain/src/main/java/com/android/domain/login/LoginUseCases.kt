package com.android.domain.login

import com.android.domain.login.models.User

interface LoginUseCases {
   suspend fun login(email: String, password: String): User
}