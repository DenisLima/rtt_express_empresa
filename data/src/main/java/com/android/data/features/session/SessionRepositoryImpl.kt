package com.android.data.features.session

import com.android.data.features.session.datasources.SessionLocalSource
import com.android.domain.features.session.SessionRepository
import com.android.domain.features.session.models.User
import io.reactivex.Single

class SessionRepositoryImpl(
    private val sessionLocalSource: SessionLocalSource
): SessionRepository {
    override fun getUserLogged(): Single<User> {
        return sessionLocalSource
            .getLoggedUser()
            .map {
                User(
                    id = it.id,
                    email = it.email,
                    fullName = it.fullName,
                    password = it.password,
                    token = it.token,
                    status = it.status
                )
            }
    }
}