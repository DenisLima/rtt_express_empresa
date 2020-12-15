package com.android.presentation.features.loadings.generateloadings.form

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.features.loadings.models.Charterer
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseFragment
import com.android.presentation.features.loadings.generateloadings.adapter.GenerateLoadingsCharterersAdapter
import kotlinx.android.synthetic.main.fragment_list_charterer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateLoadingsFormFragment : BaseFragment(),
    GenerateLoadingsCharterersAdapter.OnAddItem,
    GenerateLoadingsCharterersAdapter.OnRemoveItem {

    private val viewModel by viewModel<GenerateLoadingsFormFragmentViewModel>()
    private var totalCharactere = 0
    private var listCharacters = listOf<Charterer>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_charterer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareObservers()
        viewModel.getCharacterersList()
        initComponents()
    }

    private fun initComponents() {

        btnSendLoadings.setOnClickListener {
            viewModel.saveLoading()
        }

    }

    private fun initRecyclerView(charterers: List<Charterer>) {

        val layoutManager = LinearLayoutManager(context)

        with(recyclerCharterer) {
            adapter = GenerateLoadingsCharterersAdapter(
                charterers,
                context,
                this@GenerateLoadingsFormFragment,
                this@GenerateLoadingsFormFragment
            )
            this.layoutManager = layoutManager
        }

    }

    @SuppressLint("StringFormatMatches")
    private fun prepareObservers() {

        viewModel.getLoading()
            .observeOn(this) {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }

        viewModel.getCharacterers()
            .observeOn(this) {
                initRecyclerView(it.list)
                tvChartererTotaly.text = getString(R.string.loading_selecteds_header, "0", it.quantity)
                totalCharactere = it.quantity
                listCharacters = it.list
            }

        viewModel.getCharactereHeader()
            .observeOn(this) {
                tvChartererTotaly.text = getString(R.string.loading_selecteds_header, it, totalCharactere.toString())
            }

        viewModel.getNavigateToConfirm()
            .observeOn(this) {
                navControllerLoadings.navigate(R.id.action_generateLoadingsFormFragment_to_generateLoadingsConfirmFragment)
            }

    }

    override fun onItemClick(charterer: Charterer) {
        viewModel.addCharactere(charterer)
    }

    override fun onItemRemoveClick(charterer: Charterer) {
        viewModel.removeCharactere(charterer)
    }

}