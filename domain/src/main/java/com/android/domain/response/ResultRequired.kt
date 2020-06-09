package com.android.domain.response

sealed class ResultRequired<out T> {
    data class Success<out T>(val result: T): ResultRequired<T>()
    data class Error(val throwable: Throwable): ResultRequired<Nothing>()
}