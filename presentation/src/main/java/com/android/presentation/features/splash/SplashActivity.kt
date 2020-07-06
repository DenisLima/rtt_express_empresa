package com.android.presentation.features.splash

import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.android.presentation.R
import com.android.presentation.databinding.ActivityMainBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import com.android.presentation.home.HomeActivity
import com.android.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: BaseActivity() {
    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        changeToLogin()
        prepareObservers()

    }

    fun changeToLogin (){

        Handler().postDelayed({
            viewModel.checkNavigate()
        }, 5000)

    }

    private fun prepareObservers() {
        viewModel.getNavigateToHome()
            .observeOn(this) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }

        viewModel.getNavigateToLogin()
            .observeOn(this) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
    }
}