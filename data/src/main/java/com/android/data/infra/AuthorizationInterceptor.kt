package com.android.data.infra

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

val pref= SharedPreferences().getSharedPreferences(Context.MODE_PRIVATE)

val editor = pref.edit()

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainResponse = chain.proceed(chain.request())
        val token = mainResponse.header(AUTHORIZATION)

        if (mainResponse.code == 200) {
            //Salvar o token na SharedPreferences
            editor.putString("token",token)
            editor.commit()
            Log.e("HEADERS", token)
        }

        return mainResponse
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}