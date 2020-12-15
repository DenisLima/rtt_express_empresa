package com.android.presentation.features.loadings.generateloadings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loadings.GenerateLoadingsUseCases
import com.android.domain.features.loadings.models.Client
import com.android.domain.features.loadings.models.Vehicle
import com.android.presentation.features.general.bases.BaseViewModel
import com.android.presentation.features.loadings.generateloadings.registrationdata.PLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateLoadingsFragmentViewModel(
    private val generateLoadingsUseCases: GenerateLoadingsUseCases,
    private val pLoading: PLoading
) : BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessage(): LiveData<String> = errorMessageLv

    private val customersListLv = MutableLiveData<List<Client>>()
    fun getCustomersList(): LiveData<List<Client>> = customersListLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = isLoadingLv

    private val vehiclesListLv = MutableLiveData<List<Vehicle>>()
    fun getVehiclesList(): LiveData<List<Vehicle>> = vehiclesListLv

    fun getClients() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val clients = generateLoadingsUseCases.getClients()
                customersListLv.postValue(clients.map { it })
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
                vehiclesListLv.postValue(vehicles.map { it })
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }
        }
    }

    fun mapperObjectToNavigation(presentationLoading: PLoading) {
        pLoading.client = presentationLoading.client
        pLoading.description = presentationLoading.description
        pLoading.observation = presentationLoading.observation
        pLoading.price = presentationLoading.price
        pLoading.quantity = presentationLoading.quantity
        pLoading.vehicleType = presentationLoading.vehicleType
        pLoading.withdrawalDate = presentationLoading.withdrawalDate
        pLoading.withdrawalHour = presentationLoading.withdrawalHour
    }

}