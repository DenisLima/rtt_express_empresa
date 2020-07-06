package com.android.data.features.general.extension

import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

fun RxSharedPreferences.putString(key: String, value: String): Completable {
    return Completable.fromAction { getString(key).set(value) }
}

fun RxSharedPreferences.singleForString(key: String): Single<String> {
    return getString(key).asSingle()
}

fun <T> Preference<T>.asSingle(): Single<T> {
    return asObservable().firstOrError()
}