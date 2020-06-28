package com.android.presentation.features.generalregister

import android.os.Bundle
import android.os.PersistableBundle
import com.android.presentation.R
import com.android.presentation.features.general.bases.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class GeneralRegisterActivity : BaseActivity() {

    private val viewModel: GeneralRegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_layout)
        viewModel.registerGeneral()

    }
}