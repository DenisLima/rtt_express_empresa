package com.android.presentation.features.general.bases

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.presentation.R

open class BaseFragment: Fragment() {

    val navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
    }
}