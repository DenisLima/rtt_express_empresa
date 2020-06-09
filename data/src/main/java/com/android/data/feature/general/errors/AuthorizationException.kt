package com.android.data.feature.general.errors

/**
 * This object represents an authentication error.
 */
object AuthorizationException : ApiException(401, "Authentication error")