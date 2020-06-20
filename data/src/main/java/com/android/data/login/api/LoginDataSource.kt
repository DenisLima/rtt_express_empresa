package com.android.data.login.api

import com.android.domain.login.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginDataSource {
    @POST("/login")
    suspend fun login(@Body email: String, @Body password: String): User
}