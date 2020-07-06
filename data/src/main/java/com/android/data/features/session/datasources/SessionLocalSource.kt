package com.android.data.features.session.datasources

import com.android.data.features.session.models.DUser
import io.reactivex.Single

interface SessionLocalSource {
    fun saveLoggedUser(user: DUser): Boolean
    fun getLoggedUser(): Single<DUser>
}