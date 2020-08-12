package com.android.presentation.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.android.presentation.R
import com.android.presentation.features.general.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.toolbar.*

class NavigationMenuMain: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        setSupportActionBar(toolbar)
        initComponents()

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun initComponents() {

        menuNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.page_2 -> {
                    navController
                        .navigate(R.id.action_homeFragment_to_generalRegisterActivity)
                    true
                }
                R.id.page_3 -> {
                    navController.navigate(R.id.action_homeFragment_to_validationCharterersRegister)
                    true
                }
                else -> {
                    true
                }
            }
        }

    }

}