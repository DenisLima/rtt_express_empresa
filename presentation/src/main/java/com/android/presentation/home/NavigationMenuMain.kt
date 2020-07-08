package com.android.presentation.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.android.presentation.R
import com.android.presentation.features.general.bases.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class NavigationMenuMain: BaseActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}