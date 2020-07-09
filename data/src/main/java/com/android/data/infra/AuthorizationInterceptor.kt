package com.android.data.infra

import android.util.Log
import com.android.data.features.session.datasources.SessionLocalSource
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor(
    private val sessionLocalSource: SessionLocalSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //val mainResponse = chain.proceed(chain.request())
        //val token = mainResponse.header(AUTHORIZATION)

        //if (mainResponse.code == 200) {
            //preferences!!.putString(AUTHORIZATION, token!!)
            //Log.e("HEADERS", token)
        //}

        val request = chain.request()
        val requestBuilder = request.newBuilder().apply {

            if (request.header(NO_AUTHORIZATION_KEY) == null) {
                addHeader(AUTHORIZATION, sessionLocalSource.getLoggedUser().map {
                it.token}.blockingGet())
            }
        }

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val NO_AUTHORIZATION_KEY = "No-Auth"

        const val NO_AUTHORIZATION_HEADER = "$NO_AUTHORIZATION_KEY: true"
    }
}