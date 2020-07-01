package com.android.data.session

import com.android.data.features.general.extension.putString
import com.android.data.features.general.extension.singleForString
import com.android.domain.features.general.LoggedUserNotFoundException
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

class SessionLocalSourceImp(
    private val sessionPreferences: RxSharedPreferences
): SessionLocalSource {

    override fun savedLoggedUser(token: String): Completable {
        return sessionPreferences
            .putString(
                LOGGED_USER_KEY,
                token
            )
    }

    override fun getLoggedUser(): Single<String> {
        return sessionPreferences
            .singleForString(LOGGED_USER_KEY)
            .onErrorResumeNext { Single.error(LoggedUserNotFoundException) }
    }

    companion object {
        const val LOGGED_USER_KEY = "l-u-k"
    }

}