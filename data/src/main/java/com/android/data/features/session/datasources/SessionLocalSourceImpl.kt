package com.android.data.features.session.datasources

import android.content.Context
import android.content.SharedPreferences
import com.android.data.features.general.extension.putString
import com.android.data.features.general.extension.singleForString
import com.android.data.features.session.models.DUser
import com.android.domain.features.general.LoggedUserNotFoundException
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception

class SessionLocalSourceImpl(
    private val gson: Gson,
    private val ctx: Context
) : SessionLocalSource {

    private val prefs: SharedPreferences = ctx.getSharedPreferences(
        APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun saveLoggedUser(user: DUser): Boolean {
        try {
            prefs
                .edit()
                .putString(LOGGED_USER_KEY, gson.toJson(user))
                .apply()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getLoggedUser(): Single<DUser> {
        val user = prefs.getString(LOGGED_USER_KEY, null)
        if (user != null) {
            return Single.just(
                gson.fromJson(user, DUser::class.java)
            )
        } else {
           return Single.error<DUser>(Exception())
        }
    }

    companion object {
        const val APP_PREFERENCES = "sp1"
        const val LOGGED_USER_KEY = "l-u-k"
    }
}