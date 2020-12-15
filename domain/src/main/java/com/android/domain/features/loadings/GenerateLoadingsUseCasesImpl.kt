package com.android.domain.features.loadings

import com.android.domain.features.loadings.models.Charterer
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Loading
import com.android.domain.features.loadings.models.Vehicle

class GenerateLoadingsUseCasesImpl(
    private val generateLoadingsRepository: GenerateLoadingsRepository
): GenerateLoadingsUseCases {

    override suspend fun getClients(): List<Client> {
        return generateLoadingsRepository.getClients()
    }

    override suspend fun getVehicles(): List<Vehicle> {
        return generateLoadingsRepository.getVehicles()
    }

    override suspend fun getCharterers(): List<Charterer> {
        return generateLoadingsRepository.getCharterers()
    }

    override suspend fun saveLoading(loading: Loading): Loading {
        return generateLoadingsRepository.saveLoading(loading)
    }

}