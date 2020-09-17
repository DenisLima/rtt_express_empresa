package com.android.domain.features.loadings

import com.android.domain.features.loadings.models.Client

class GenerateLoadingsUseCasesImpl(
    private val generateLoadingsRepository: GenerateLoadingsRepository
): GenerateLoadingsUseCases {

    override suspend fun getClients(): List<Client> {
        return generateLoadingsRepository.getClients()
    }

}