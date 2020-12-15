package com.android.presentation.features.loadings.generateloadings.registrationdata

data class PLoading (
    var quantity: Int = 0,
    var client: Int = 0,
    var description: String = "",
    var withdrawalDate: String = "",
    var withdrawalHour: String = "",
    var price: Float = 0F,
    var vehicleType: Int = 0,
    var observation: String = ""
)