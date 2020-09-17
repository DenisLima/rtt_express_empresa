package com.android.presentation.features.loadings.generateloadings

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.domain.features.loadings.models.Client
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_generate_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateLoadingsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModel<GenerateLoadingsFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generate_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        viewModel.getClients()
        prepareObservers()
    }

    private fun initComponents() {
        btnLoadingNext.setOnClickListener {
            navControllerLoadings.navigate(R.id.action_generateLoadingsFragment_to_generateLoadingsFormFragment)
        }
    }

    private fun prepareObservers() {
        viewModel.getErrorMessage()
            .observeOn(this) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

        viewModel.getCustomersList()
            .observeOn(this) {
                initSpinner(it)
            }

        viewModel.getLoading()
            .observeOn(this) {
                if (it) {
                    progressLoadingClients.visibility = View.VISIBLE
                    spinnerLoadingClients.visibility = View.INVISIBLE
                } else {
                    spinnerLoadingClients.visibility = View.VISIBLE
                    progressLoadingClients.visibility = View.GONE
                }
            }
    }

    private fun initSpinner(costumersList: List<String>) {
        var spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, costumersList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinnerLoadingClients) {
            adapter = spinnerAdapter
            setSelection(0, true)
            onItemSelectedListener = this@GenerateLoadingsFragment
            prompt = "Selecione um cliente"
            gravity = Gravity.CENTER
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "Nada selecionado", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(context, "O que foi selecionado foi $p2", Toast.LENGTH_LONG).show()
    }

}