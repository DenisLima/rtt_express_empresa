package com.android.data.features.loadings

import com.android.data.features.loadings.datasource.GenerateLoadingsRemoteSource
import com.android.domain.features.loadings.GenerateLoadingsRepository
import com.android.domain.features.loadings.models.Client

class GenerateLoadingsRepositoryImpl(
    private val generateLoadingsRemoteSource: GenerateLoadingsRemoteSource
) : GenerateLoadingsRepository {

    override suspend fun getClients(): List<Client> {
        val clients = generateLoadingsRemoteSource.getClients()

        return clients.map {
            Client(
                id = it.id,
                name = it.name,
                cnpj = it.cnpj
            )
        }
    }

}