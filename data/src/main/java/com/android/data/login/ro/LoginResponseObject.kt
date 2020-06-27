package com.android.data.login.ro

import com.google.gson.annotations.SerializedName

data class LoginResponseObject(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
