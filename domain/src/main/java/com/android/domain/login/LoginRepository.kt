package com.android.domain.login

import com.android.domain.features.session.models.User
import com.android.domain.login.ro.LoginResultObject
import io.reactivex.Single

interface LoginRepository {
   suspend fun login (email: String, password: String): LoginResultObject
   fun isLoggedIn(): Single<Boolean>
   fun getUserLogged(): Single<User>
}