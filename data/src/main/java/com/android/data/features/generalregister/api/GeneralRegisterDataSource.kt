package com.android.data.features.generalregister.api

import com.android.data.features.generalregister.models.GeneralRegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GeneralRegisterDataSource {
    @POST("/addEnterprise")
    suspend fun registerGeneral(@Body general: GeneralRegisterRequest): Boolean
}