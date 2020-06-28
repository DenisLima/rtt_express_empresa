package com.android.presentation.features.generalregister

import androidx.lifecycle.viewModelScope
import com.android.domain.features.generalregister.GeneralRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GeneralRegisterViewModel(private val generalRegisterUseCases: GeneralRegisterUseCases) :BaseViewModel(){

    fun registerGeneral(){
viewModelScope.launch(Dispatchers.IO) {
    generalRegisterUseCases.registerGeneral(
        "1999",
        "095885"
    )
}
    }
}