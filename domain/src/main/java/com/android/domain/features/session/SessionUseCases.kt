package com.android.domain.features.session

import com.android.domain.features.session.models.User
import io.reactivex.Single

interface SessionUseCases {
    fun getUserLogged(): Single<User>
}