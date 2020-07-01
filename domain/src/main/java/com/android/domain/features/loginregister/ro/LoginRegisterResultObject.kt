package com.android.domain.features.loginregister.ro

data class LoginRegisterResultObject(
    var id: Long = 0,
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var isAcceptedTerm: Boolean = false
)