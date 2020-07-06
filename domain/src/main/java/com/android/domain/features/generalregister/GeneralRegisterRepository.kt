package com.android.domain.features.generalregister

import io.reactivex.Single

interface GeneralRegisterRepository {
    suspend fun registerGeneral(
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
    ): Boolean

}
