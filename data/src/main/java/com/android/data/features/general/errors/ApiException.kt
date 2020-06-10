package com.android.data.features.general.errors

/**
 * This interface describes an api exception.
 */
open class ApiException(
    val code: Int,
    msg: String
) : Exception(msg)