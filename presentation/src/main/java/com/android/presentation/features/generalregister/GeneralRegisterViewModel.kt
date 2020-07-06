package com.android.presentation.features.generalregister

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.android.domain.features.generalregister.GeneralRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.HttpRetryException


class GeneralRegisterViewModel(private val generalRegisterUseCases: GeneralRegisterUseCases) :
    BaseViewModel() {

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    private val messageErrorLv = MutableLiveData<String>()
    fun getErrorMessage(): LiveData<String> = messageErrorLv

    fun registerGeneral(
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
    ) {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                generalRegisterUseCases.registerGeneral(
                    razaoSocial = razaoSocial,
                    cnpj = cnpj,
                    endereco = endereco,
                    numero = numero,
                    complemento = complemento,
                    telefone1 = telefone1,
                    telefone2 = telefone2,
                    bairro = bairro,
                    cidade = cidade,
                    uf = uf,
                    cep = cep,
                    responsavelLegal = responsavelLegal,
                    site = site
                )
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                isLoadingLv.postValue(false)
                messageErrorLv.postValue(e.message)
                Log.e("Error Http: ", e.message)
            }

        }
    }



}