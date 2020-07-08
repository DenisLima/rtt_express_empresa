package com.android.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import com.android.presentation.features.loginregister.LoginRegisterActivity
import com.android.presentation.home.HomeFragment
import com.android.presentation.home.NavigationMenuMain
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.viewModel = viewModel
        prepareObservers()

        initComponents()

    }

    private fun initComponents(){

        tvRegister.setOnClickListener {
            var intent=Intent(this,LoginRegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            viewModel.checkLogin(
                email = teEmailLogin.text.toString(),
                password = tePassword.text.toString()
            )
        }

        btnRememberPassword.setOnClickListener {
            viewModel.checkPrefs()
        }

    }

    private fun prepareObservers() {

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
            .observeOn(this) { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }

        viewModel
            .getEnableButton()
            .observeOn(this) {
                btnLogin.isEnabled = it
            }

        viewModel.getNavigateToHome()
            .observeOn(this) {
                val intent = Intent(this, NavigationMenuMain::class.java)
                startActivity(intent)
            }

    }


}