package com.android.domain.login.models

data class  User(
    val id: Long,
    val email:String,
    val password: String,
    val fullName: String,
    val termAccepTed: Boolean
)