package com.android.data.features.loadings

import com.android.data.features.loadings.datasource.GenerateLoadingsRemoteSource
import com.android.data.features.loadings.models.DLoading
import com.android.domain.features.loadings.GenerateLoadingsRepository
import com.android.domain.features.loadings.models.Charterer
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Loading
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

    override suspend fun saveLoading(loading: Loading): Loading {
        val requestLoading = generateLoadingsRemoteSource.saveLoading(
            DLoading(
                quantity = loading.quantity,
                client = loading.client,
                description = loading.description,
                withdrawalDate = loading.withdrawalDate,
                withdrawalHour = loading.withdrawalHour,
                price = loading.price,
                vehicleType = loading.vehicleType,
                observation = loading.observation,
                idCharterer = loading.idCharterer,
                id = loading.id
            )
        )

        return Loading(
            quantity = requestLoading.quantity,
            client = requestLoading.client,
            description = requestLoading.description,
            withdrawalDate = requestLoading.withdrawalDate,
            withdrawalHour = requestLoading.withdrawalHour,
            price = requestLoading.price,
            vehicleType = requestLoading.vehicleType,
            observation = requestLoading.observation,
            idCharterer = requestLoading.idCharterer,
            id = requestLoading.id
        )

    }

}