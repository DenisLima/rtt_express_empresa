package com.android.presentation.features.general.bases

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.presentation.R

open class BaseFragment : Fragment() {

    val navController: NavController by lazy {
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
    }

    fun setToolbarTitle(title: String) {
        val toolbarTitle = requireActivity().findViewById<TextView>(R.id.txtHomeWelcomeMsg)
        toolbarTitle.text = getString(R.string.hello, title)
    }
}