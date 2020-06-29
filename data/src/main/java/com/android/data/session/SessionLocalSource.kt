package com.android.data.session

import com.android.domain.login.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface SessionLocalSource {
    fun savedLoggedUser(token: String): Completable
    fun getLoggedUser(): Single<String>
}