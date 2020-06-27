package com.android.presentation.features.splash

import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.android.presentation.R
import com.android.presentation.databinding.ActivityMainBinding
import com.android.presentation.features.general.bases.BaseActivity
import com.android.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: BaseActivity() {
    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        changeToLogin()

    }

    fun changeToLogin (){
        val intent = Intent(this, LoginActivity:: class.java)

        Handler().postDelayed({
            intent.change()
        }, 5000)
    }

    fun Intent.change(){
        startActivity(this )
        finish()
    }
}