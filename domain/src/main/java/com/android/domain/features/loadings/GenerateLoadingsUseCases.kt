package com.android.domain.features.loadings

import com.android.domain.features.loadings.models.Charterer
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Loading
import com.android.domain.features.loadings.models.Vehicle

interface GenerateLoadingsUseCases {
    suspend fun getClients(): List<Client>
    suspend fun getVehicles(): List<Vehicle>
    suspend fun getCharterers(): List<Charterer>
    suspend fun saveLoading(loading: Loading): Loading
}