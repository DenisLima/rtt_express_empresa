package com.android.data.features.loadings.datasource

import com.android.data.features.loadings.models.DCharterer
import com.android.data.features.loadings.models.DClient
import com.android.data.features.loadings.models.DVehicle
import retrofit2.http.GET

interface GenerateLoadingsRemoteSource {

    @GET("/customers")
    suspend fun getClients(): List<DClient>

    @GET("/vehicles")
    suspend fun getVehicles(): List<DVehicle>

    @GET("/charterers")
    suspend fun getCharterers(): List<DCharterer>

}