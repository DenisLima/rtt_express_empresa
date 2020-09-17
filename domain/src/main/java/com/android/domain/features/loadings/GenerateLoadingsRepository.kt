package com.android.domain.features.loadings

import com.android.domain.features.loadings.models.Client

interface GenerateLoadingsRepository {
    suspend fun getClients(): List<Client>
}