package com.android.presentation.features.loadings.generateloadings.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                initRecyclerView(it)
            }
    }

    override fun onItemClick(position: String) {
        Toast.makeText(context, "adicionou $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemRemoveClick(position: String) {
        Toast.makeText(context, "removeu $position", Toast.LENGTH_SHORT).show()
    }

}