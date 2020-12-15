package com.android.presentation.features.loadings.generateloadings.models

import com.android.domain.features.loadings.models.Charterer

data class PCharterers (
    val quantity: Int,
    val list: List<Charterer>
)