package com.android.presentation.features.general.bases

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.android.data.features.general.errors.AuthorizationException
import com.android.domain.features.general.LoggedUserNotFoundException
import com.android.presentation.extensions.ignoreErrorsAndSubscribeOnUi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.get

open class BaseViewModel: ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }

    /**
     * Subscribes on ui thread.
     */
    inline fun <T : Any> Observable<T>.subscribeOnUi(
        crossinline onSubscribe: () -> Unit = {},
        crossinline afterTerminate: () -> Unit = {},
        crossinline onCompleted: () -> Unit = {},
        crossinline onError: (Throwable) -> Boolean = { false },
        crossinline onNext: (T) -> Unit = {}
    ): Disposable {
        return observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe() }
            .doAfterTerminate { afterTerminate() }
            .subscribeBy(
                onNext = { next: T -> onNext(next) },
                onComplete = { onCompleted() },
                onError = { error ->
                    if (!onError(error)) {
                        //handleError(error)
                    }
                }
            )
    }

}