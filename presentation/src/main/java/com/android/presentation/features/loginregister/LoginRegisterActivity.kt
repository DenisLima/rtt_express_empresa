package com.android.presentation.features.loginregister

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginRegisterBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_login_register.*
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
    }

    fun initComponents() {


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
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }

        viewModel.getUserName()
            .observeOn(this) { text ->
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }

    }

}