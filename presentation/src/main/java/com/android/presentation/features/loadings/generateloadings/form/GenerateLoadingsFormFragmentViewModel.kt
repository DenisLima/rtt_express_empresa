package com.android.presentation.features.loadings.generateloadings.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loadings.GenerateLoadingsUseCases
import com.android.domain.features.loadings.models.Charterer
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateLoadingsFormFragmentViewModel(
    private val generateLoadingsUseCases: GenerateLoadingsUseCases
): BaseViewModel() {

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = isLoadingLv

    private val characterersListLv = MutableLiveData<List<Charterer>>()
    fun getCharacterers(): LiveData<List<Charterer>> = characterersListLv

    private val messageErrorLv = MutableLiveData<String>()
    fun getMessageError(): LiveData<String> = messageErrorLv

    fun getCharacterersList() {
        isLoadingLv.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val charterers = generateLoadingsUseCases.getCharterers()
                characterersListLv.postValue(charterers)
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                messageErrorLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }
        }
    }

}