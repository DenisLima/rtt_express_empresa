package com.android.presentation.login

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.android.presentation.R
import com.android.presentation.databinding.ActivityLoginBinding
import com.android.presentation.databinding.ActivityMainBinding
import com.android.presentation.features.general.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)

        //initComponents()
    }
/*
    private fun initComponents() {
        teEmailLogin.addTextChangedListener {

        }
    }


 */

}