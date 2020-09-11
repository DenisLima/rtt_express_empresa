package com.android.domain.features.session

import com.android.domain.features.session.models.User
import com.android.domain.login.LoginRepository
import io.reactivex.Single

class SessionUseCasesImpl(
    private val loginRepository: LoginRepository
): SessionUseCases {
    override fun getUserLogged(): Single<User> {
        return loginRepository.getUserLogged()
    }
}