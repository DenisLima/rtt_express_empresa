package com.android.presentation.home

import android.os.Bundle
import com.android.presentation.R
import com.android.presentation.features.general.bases.BaseActivity

class HomeActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}