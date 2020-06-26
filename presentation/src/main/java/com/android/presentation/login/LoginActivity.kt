package com.android.presentation.login

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.viewModel = viewModel
        prepareObservers()

    }

    fun prepareObservers() {

        viewModel.isLoading().observeOn(this) { isLoading ->
            when {
                isLoading -> {
                    showLoading()
                }
                else -> {
                    hideLoading()
                }
            }
        }

        viewModel.getErrorMessageLv()
            .observeOn(this){ error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }

    }


}