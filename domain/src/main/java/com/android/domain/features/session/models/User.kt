package com.android.domain.features.session.models

data class User (
    var id: Long = 0,
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var token: String = "",
    var status: Int = 1
)