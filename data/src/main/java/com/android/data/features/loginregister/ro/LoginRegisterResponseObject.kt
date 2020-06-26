package com.android.data.features.loginregister.ro

import com.google.gson.annotations.SerializedName

data class LoginRegisterResponseObject(
    @SerializedName("id") val id: Long,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("isAcceptedTerm") val isAcceptedTerm: Boolean
)