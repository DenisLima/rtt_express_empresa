package com.android.data.login

import com.android.data.login.api.LoginDataSource
import com.android.domain.login.LoginRepository
import com.android.domain.login.models.User

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource): LoginRepository {
    override suspend fun login(email: String, password: String): User {
      return loginDataSource.login(email,password)
    }
}