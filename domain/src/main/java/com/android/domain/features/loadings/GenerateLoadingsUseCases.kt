package com.android.domain.features.loadings

import com.android.domain.features.loadings.models.Client

interface GenerateLoadingsUseCases {
    suspend fun getClients(): List<Client>
}