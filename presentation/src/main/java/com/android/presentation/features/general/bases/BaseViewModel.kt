package com.android.presentation.features.general.bases


import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.inject

open class BaseViewModel: ViewModel() {


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

    /**
     * Subscribes on ui thread.
     */
    inline fun <T : Any> Single<T>.subscribeOnUi(
        crossinline onSubscribe: () -> Unit = {},
        crossinline afterTerminate: () -> Unit = {},
        crossinline onError: (Throwable) -> Boolean = { false },
        crossinline onSuccess: (T) -> Unit = {}
    ): Disposable {
        return observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe() }
            .doAfterTerminate { afterTerminate() }
            .subscribeBy(
                onSuccess = { result: T -> onSuccess(result) },
                onError = { error ->
                    if (!onError(error)) {
                        //handleError(error)
                    }
                }
            )
    }

    /**
     * Subscribes on ui thread.
     */
    inline fun Completable.subscribeOnUi(
        crossinline onSubscribe: () -> Unit = {},
        crossinline afterTerminate: () -> Unit = {},
        crossinline onError: (Throwable) -> Boolean = { false },
        crossinline onCompleted: () -> Unit = {}
    ): Disposable {
        return observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe() }
            .doAfterTerminate { afterTerminate() }
            .subscribeBy(
                onComplete = { onCompleted() },
                onError = { error ->
                    if (!onError(error)) {
                        //handleError(error)
                    }
                }
            )
    }

    /**
     * Subscribes on ui thread.
     */
    inline fun <T : Any> Flowable<T>.subscribeOnUi(
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