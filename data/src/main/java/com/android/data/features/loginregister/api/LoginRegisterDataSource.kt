package com.android.data.features.loginregister.api

import com.android.data.features.loginregister.models.LoginRegisterModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginRegisterDataSource {
    @Headers("Content-Type: application/json")
    @POST("/signup")
    suspend fun register(
        @Body loginRegister: LoginRegisterModel

    ): Boolean
}