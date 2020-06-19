package com.android.presentation.features.loginregister

import android.os.Bundle
import com.android.presentation.R
import com.android.presentation.features.general.BaseActivity

class LoginRegisterActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
    }
}