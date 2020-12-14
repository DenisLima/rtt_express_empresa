package com.android.data.features.loadings

import com.android.data.features.loadings.datasource.GenerateLoadingsRemoteSource
import com.android.domain.features.loadings.GenerateLoadingsRepository
import com.android.domain.features.loadings.models.Charterer
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Vehicle

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

    override suspend fun getVehicles(): List<Vehicle> {
        val vehicles = generateLoadingsRemoteSource.getVehicles()

        return vehicles.map {
            Vehicle(
                id = it.id,
                name = it.name,
                type = it.type
            )
        }
    }

    override suspend fun getCharterers(): List<Charterer> {
        val charterers = generateLoadingsRemoteSource.getCharterers()

        return charterers.map {
            Charterer(
                id = it.id,
                name = it.name
            )
        }
    }

}