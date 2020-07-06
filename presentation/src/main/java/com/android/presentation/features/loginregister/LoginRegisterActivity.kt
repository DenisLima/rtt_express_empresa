package com.android.presentation.features.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginRegisterBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import com.android.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_android_jobs_list.*
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginRegisterBinding
    private val viewModel: LoginRegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_register)
        binding.viewModel = viewModel

        prepareObservers()
        initComponents()
        setBackOnActionBarEnabled(true)
        setSupportActionBar(toolbar)
    }

    fun initComponents() {

        cbTermsUse.setOnClickListener {
            viewModel.setTermAccepted(cbTermsUse.isChecked)
        }

        btnRegister.setOnClickListener {
            viewModel.loginRegisterModel(
                teNameLoginRegister.text.toString(),
                teEmailRegister.text.toString(),
                tePasswordRegister.text.toString()
            )
        }
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
            .observeOn(this) { error ->
                Toast.makeText(this, "Erro de conexão, tente novamente!", Toast.LENGTH_SHORT).show()
            }

        viewModel.getEnableButton()
            .observeOn(this) {
                btnRegister.isEnabled = it
            }

        viewModel.getNavigateToLogin()
            .observeOn(this) {
                when {
                    it -> {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

    }

}