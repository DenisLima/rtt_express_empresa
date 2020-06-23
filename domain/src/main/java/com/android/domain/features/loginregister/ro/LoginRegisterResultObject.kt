package com.android.domain.features.loginregister.ro

data class LoginRegisterResultObject(
    val id: Long,
    val fullName: String,
    val email: String,
    val password: String,
    val isAcceptedTerm: Boolean
)