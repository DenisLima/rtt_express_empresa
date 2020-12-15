package com.android.presentation.features.loadings.generateloadings.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_generate_loading_confirm.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateLoadingsConfirmFragment : BaseFragment() {

    private val viewModel by viewModel<GenerateLoadingsConfirmViewModel>()
    private val listOrders by lazy {
        arguments?.getString("amount")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generate_loading_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initViews()
        prepareObservers()
        initComponents()
    }

    private fun initComponents() {
        btnOkLoading.setOnClickListener {
            navControllerLoadings.navigate(R.id.action_generateLoadingsConfirmFragment_to_generateLoadingsFragment)
        }
    }

    private fun prepareObservers() {
        viewModel.getListOrders()
            .observeOn(this) { list ->
                var orders = ""
                list.map {
                    orders += it
                }
                tvOrdersLoading.text = orders
            }
    }

}