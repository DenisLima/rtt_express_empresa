package com.android.presentation.features.loadings.generateloadings.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loadings.GenerateLoadingsUseCases
import com.android.domain.features.loadings.models.Charterer
import com.android.domain.features.loadings.models.Loading
import com.android.presentation.features.general.bases.BaseViewModel
import com.android.presentation.features.loadings.generateloadings.models.PCharterers
import com.android.presentation.features.loadings.generateloadings.registrationdata.PListOrders
import com.android.presentation.features.loadings.generateloadings.registrationdata.PLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Time

class GenerateLoadingsFormFragmentViewModel(
    private val generateLoadingsUseCases: GenerateLoadingsUseCases,
    private val pLoading: PLoading,
    private val pListOrders: PListOrders
): BaseViewModel() {

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = isLoadingLv

    private val characterersListLv = MutableLiveData<PCharterers>()
    fun getCharacterers(): LiveData<PCharterers> = characterersListLv

    private val messageErrorLv = MutableLiveData<String>()
    fun getMessageError(): LiveData<String> = messageErrorLv

    private var totalCharactere = 0
    private var listCharterer = arrayListOf<Charterer>()

    private val charactereHeaderLv = MutableLiveData<Int>()
    fun getCharactereHeader(): LiveData<Int> = charactereHeaderLv

    private var checkInsert = 0

    private val navigateToConfirm = MutableLiveData<Unit>()
    fun getNavigateToConfirm(): LiveData<Unit> = navigateToConfirm

    fun getCharacterersList() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val charterers = generateLoadingsUseCases.getCharterers()
                characterersListLv.postValue(PCharterers(pLoading.quantity, charterers))
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                messageErrorLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }
        }
    }

    fun saveLoading() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (i in listCharterer.indices) {
                    val orderLoading = generateLoadingsUseCases.saveLoading(
                        Loading(
                            quantity = pLoading.quantity,
                            client = pLoading.client,
                            description = pLoading.description,
                            withdrawalDate = pLoading.withdrawalDate,
                            withdrawalHour = pLoading.withdrawalHour,
                            price = pLoading.price,
                            vehicleType = pLoading.vehicleType,
                            observation = pLoading.observation,
                            idCharterer = listCharterer[i].id
                        )
                    )
                    pListOrders.listOrders.add("#${orderLoading.id} ")
                }
                isLoadingLv.postValue(false)
                navigateToConfirm.postValue(Unit)
            } catch (e: Exception) {
                isLoadingLv.postValue(false)
            }
        }
    }

    fun addCharactere(charterer: Charterer) {
        totalCharactere += 1
        charactereHeaderLv.value = totalCharactere
        listCharterer.add(charterer)
    }

    fun removeCharactere(charterer: Charterer) {
        totalCharactere -= 1
        charactereHeaderLv.value = totalCharactere
        listCharterer.remove(charterer)
    }

}