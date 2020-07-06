package com.android.domain.features.splash

import com.android.domain.features.session.models.User
import com.android.domain.login.LoginRepository
import io.reactivex.Single

class SplashUseCasesImpl(
    private val loginRepository: LoginRepository
): SplashUseCases {
    override fun isLoggedIn(): Single<Boolean> {
        return loginRepository
            .isLoggedIn()
    }

    override fun getUserLogged(): Single<User> {
        return loginRepository
            .getUserLogged()
    }

}