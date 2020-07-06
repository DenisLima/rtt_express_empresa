package com.android.data.infra

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val mainResponse = chain.proceed(chain.request())
        val token = mainResponse.header(AUTHORIZATION)

        if (mainResponse.code == 200) {
            //preferences!!.putString(AUTHORIZATION, token!!)
            //Log.e("HEADERS", token)
        }

        return mainResponse
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}