package com.android.data.features.loadings.models

data class DLoading (
    val id: Int,
    val quantity: Int,
    val client: Int,
    val description: String,
    val withdrawalDate: String,
    val withdrawalHour: String,
    val price: Float,
    val vehicleType: Int,
    val observation: String,
    val idCharterer: Int
)