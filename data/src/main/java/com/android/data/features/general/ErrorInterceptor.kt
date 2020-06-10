package com.android.data.features.general

import air.br.com.alelo.mobile.android.domain.features.general.errors.*
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor(
    private val gson: Gson,
    private val isLoggingEnabled: Boolean
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(request).apply {
            if (!isSuccessful) {
                //Timber.d("Error received from ${request.url}: $code")

                /*throw when (code) {
                    AuthorizationException.code -> AuthorizationException
                    ForbiddenException.code -> ForbiddenException
                    Resources.NotFoundException.code -> Resources.NotFoundException
                    UnknownException.code -> UnknownException
                    TooManyRequestsException.code -> TooManyRequestsException
                    UnProcessableEntityException.CODE -> UnProcessableEntityException.from(body)
                    InternalServerException.code -> InternalServerException
                    else -> ApiException(code, body?.string() ?: message)
                }.printStackTrace(isLoggingEnabled)*/
            }
        }
    }

    /**
     * Builds an [UnProcessableEntityException] from a response body.
     */
    /*private fun UnProcessableEntityException.Companion.from(
        body: ResponseBody?
    ): UnProcessableEntityException {
        val errors: List<ErrorResponseObject> =
            try {
                val listType = object : TypeToken<List<ErrorResponseObject>>() {}.type
                gson.fromJson(body?.string() ?: "[]", listType)
            } catch (ignored: Exception) {
                ignored.printStackTrace()
                emptyList()
            }

        return UnProcessableEntityException(errors.map { (field, _) -> FieldException(field) })
    }*/

}