package com.android.data.features.generalregister

import com.android.data.features.generalregister.api.GeneralRegisterDataSource
import com.android.data.features.generalregister.models.GeneralRegisterRequest
import com.android.data.session.SessionLocalSource
import com.android.domain.features.generalregister.GeneralRegisterRepository
import io.reactivex.Completable
import io.reactivex.Single

class GeneralRegisterRepositoryImpl(
    private val generalRegisterDataSource: GeneralRegisterDataSource,
    private val sessionLocalSource: SessionLocalSource
) :
    GeneralRegisterRepository {
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
        return generalRegisterDataSource.registerGeneral(
            GeneralRegisterRequest(
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
        )
    }

    override fun putToken(token: String): Single<Boolean> {
        sessionLocalSource.savedLoggedUser(token)
        return Single.just(true)
    }
}