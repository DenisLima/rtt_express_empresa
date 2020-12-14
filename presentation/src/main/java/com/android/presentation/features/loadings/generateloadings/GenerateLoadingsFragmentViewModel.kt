package com.android.presentation.features.loadings.generateloadings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loadings.GenerateLoadingsUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateLoadingsFragmentViewModel(
    private val generateLoadingsUseCases: GenerateLoadingsUseCases
) : BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessage(): LiveData<String> = errorMessageLv

    private val customersListLv = MutableLiveData<List<String>>()
    fun getCustomersList(): LiveData<List<String>> = customersListLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = isLoadingLv

    private val vehiclesListLv = MutableLiveData<List<String>>()
    fun getVehiclesList(): LiveData<List<String>> = vehiclesListLv

    fun getClients() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val clients = generateLoadingsUseCases.getClients()
                customersListLv.postValue(clients.map { it.name })
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }
        }
    }

    fun getVehicles() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val vehicles = generateLoadingsUseCases.getVehicles()
                vehiclesListLv.postValue(vehicles.map { it.name })
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }
        }
    }

}