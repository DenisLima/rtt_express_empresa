package com.android.data.login.api

import com.android.data.infra.AuthorizationInterceptor
import com.android.data.login.models.LoginModel
import com.android.data.login.ro.LoginResponseObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginDataSource {
    @Headers(AuthorizationInterceptor.NO_AUTHORIZATION_HEADER)
    @POST("/login")
    suspend fun login(
        @Body login: LoginModel
    ): LoginResponseObject
}