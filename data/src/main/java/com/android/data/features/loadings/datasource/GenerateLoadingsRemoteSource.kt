package com.android.data.features.loadings.datasource

import com.android.data.features.loadings.models.DClient
import retrofit2.http.GET

interface GenerateLoadingsRemoteSource {

    @GET("/customers")
    suspend fun getClients(): List<DClient>

}