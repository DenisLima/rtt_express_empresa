package com.android.presentation.features.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.presentation.R
import com.android.presentation.databinding.ActivityMainBinding
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.BaseActivity
import com.android.presentation.features.list.AndroidJobsListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.showAndroidJobsLiveData.observe(this, Observer { isShow ->
            when(isShow) {
                true -> { startActivity(AndroidJobsListActivity.launchIntent(this)) }
            }
        })

        viewModel.outAppLiveData.observe(this, Observer { isOut ->
            when(isOut) {
                true -> { finish() }
            }
        })

    }
}