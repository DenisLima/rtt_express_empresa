package com.android.presentation.features.loadings

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import com.android.presentation.R
import com.android.presentation.extensions.changeToolbarFont
import com.android.presentation.features.general.bases.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class NavigationLoading: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        setSupportActionBar(toolbar)
        toolbar.changeToolbarFont()
        hiddenOriginTitle()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

}