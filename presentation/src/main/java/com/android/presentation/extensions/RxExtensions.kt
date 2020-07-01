package com.android.presentation.extensions

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

fun Completable.ignoreErrorsAndSubscribeOnUi(block: () -> Unit = {}): Disposable {
    return onErrorComplete()
        .observeOn(AndroidSchedulers.mainThread())
        .doAfterTerminate(block)
        .subscribeBy(onError = { })
}