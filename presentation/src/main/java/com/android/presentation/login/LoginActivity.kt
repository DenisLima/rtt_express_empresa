package com.android.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import com.android.presentation.features.general.dialogs.AlertDialogFragment
import com.android.presentation.features.loginregister.LoginRegisterActivity
import com.android.presentation.home.HomeFragment
import com.android.presentation.home.NavigationMenuMain
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val showRegisterSuccess by lazy {
        intent.getBooleanExtra(SHOW_REGISTER_SUCCESS, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.viewModel = viewModel
        prepareObservers()

        initComponents()
        initRegisterDialog()

    }

    private fun initRegisterDialog() {
        if (showRegisterSuccess) {
            AlertDialogFragment(
                "Olá",
                "Enviamos um email para você, por favor clicar no link do email para ativar a conta!"
            ).show(supportFragmentManager, null)
        }
    }

    private fun initComponents() {

        tvRegister.setOnClickListener {
            var intent = Intent(this, LoginRegisterActivity::class.java)
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

        viewModel.getNavigateToLoginEmailMsg()
            .observeOn(this) {
                val dialog = AlertDialogFragment(
                    getString(R.string.general_alert_dialog_title),
                    getString(R.string.general_alert_dialog_description)
                )
                dialog.show(supportFragmentManager, null)
            }
        viewModel.getNavigateToGeneralRegister()
            .observeOn(this) {
                val intent = Intent(this, NavigationMenuMain::class.java)
                startActivity(intent)
            }


    }

    companion object {
        private const val SHOW_REGISTER_SUCCESS = "registerSuccess"
    }

}