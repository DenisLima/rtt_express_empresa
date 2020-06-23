package com.android.data.features.loginregister.api

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRegisterDataSource {
    @POST("/signup")
    suspend fun register(
        @Body email: String
    ): Boolean
}