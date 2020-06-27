package com.android.domain.login.ro

data class LoginResultObject (
    var id: Long = 0,
    var fullName: String = "",
    var email: String = "",
    var password: String = ""
)