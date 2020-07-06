package com.android.domain.features.splash

import com.android.domain.features.session.models.User
import io.reactivex.Single

interface SplashUseCases {
    fun isLoggedIn(): Single<Boolean>
    fun getUserLogged(): Single<User>
}