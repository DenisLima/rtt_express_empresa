package com.android.domain.features.generalregister

import io.reactivex.Single

class GeneralRegisterUseCasesImpl(private val generalRegisterRepository: GeneralRegisterRepository) :
    GeneralRegisterUseCases {
    override suspend fun registerGeneral(
        razaoSocial: String,
        cnpj: String,
        endereco: String,
        numero: String,
        complemento: String,
        telefone1: String,
        telefone2: String,
        bairro: String,
        cidade: String,
        uf: String,
        cep: String,
        responsavelLegal: String,
        site: String
    ): Boolean {
        return generalRegisterRepository.registerGeneral(
            razaoSocial,
            cnpj,
            endereco,
            numero,
            complemento,
            telefone1,
            telefone2,
            bairro,
            cidade,
            uf,
            cep,
            responsavelLegal,
            site
        )
    }

}