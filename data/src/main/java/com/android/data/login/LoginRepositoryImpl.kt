package com.android.data.login

import com.android.data.features.session.datasources.SessionLocalSource
import com.android.data.features.session.models.DUser
import com.android.data.login.api.LoginDataSource
import com.android.data.login.models.LoginModel
import com.android.domain.features.session.models.User
import com.android.domain.login.LoginRepository
import com.android.domain.login.ro.LoginResultObject
import io.reactivex.Single

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource,
    private val sessionLocalSource: SessionLocalSource
) : LoginRepository {
    override suspend fun login(email: String, password: String): LoginResultObject {

        var loginResultObject = LoginResultObject()
        var user = DUser()

        loginDataSource.login(
            LoginModel(email, password)
        ).let {

            loginResultObject!!.status = it.status

            user.email = it.email
            user.fullName = it.fullName
            user.password = it.password
            user.status = it.status
            user.token = it.token

        }

        if (user.token.isNotEmpty()) {
            loginResultObject!!.isLogged = true
            sessionLocalSource
                .saveLoggedUser(user)
        }

        return loginResultObject!!
    }

    override fun isLoggedIn(): Single<Boolean> {
        return sessionLocalSource
            .getLoggedUser()
            .flatMap {
                Single.just(true)
            }
            .onErrorResumeNext { Single.just(false) }
    }

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