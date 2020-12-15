package com.android.presentation.features.loadings.generateloadings.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.presentation.features.general.bases.BaseViewModel
import com.android.presentation.features.loadings.generateloadings.registrationdata.PListOrders

class GenerateLoadingsConfirmViewModel(
    private val pListOrders: PListOrders
): BaseViewModel() {

    private val listOrders = MutableLiveData<ArrayList<String>>()
    fun getListOrders(): LiveData<ArrayList<String>> = listOrders

    fun initViews() {
        listOrders.value = pListOrders.listOrders
    }

}