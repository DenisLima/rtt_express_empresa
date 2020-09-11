package com.android.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        prepareObservers()
    }

    private fun initComponents() {
        viewModel.getUser()
    }

    private fun prepareObservers() {
        viewModel.getErrorMessage()
            .observeOn(this) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

        viewModel.getUserInfo()
            .observeOn(this) {
                setToolbarTitle(it.fullName)
            }
    }

}