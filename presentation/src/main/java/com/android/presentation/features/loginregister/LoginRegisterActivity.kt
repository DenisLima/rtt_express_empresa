package com.android.presentation.features.loginregister

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginRegisterBinding
import com.android.presentation.features.general.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterActivity: BaseActivity() {
    private lateinit var binding: ActivityLoginRegisterBinding
    private val viewModel: LoginRegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login_register)
        binding.viewModel=viewModel
    }
}