package com.android.presentation.features.loadings.generateloadings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.features.loadings.models.Charterer
import com.android.presentation.R
import kotlinx.android.synthetic.main.item_charterers_list.view.*

class GenerateLoadingsCharterersAdapter(
    private val dataSet: List<Charterer>,
    private val context: Context,
    private val listener: OnAddItem,
    private val listenerRemove: OnRemoveItem
) :
    RecyclerView.Adapter<GenerateLoadingsCharterersAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(charterer: Charterer, listener: OnAddItem, listenerRemove: OnRemoveItem) {
            val name = itemView.tvChartererName
            val checkBox = itemView.checkboxCharterer

            name.text = charterer.name
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    listener.onItemClick(charterer.name)
                } else {
                    listenerRemove.onItemRemoveClick(charterer.name)
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_charterers_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val characterers = dataSet[position]
        holder?.let {
            it.bindView(characterers, listener, listenerRemove)
        }
    }

    interface OnAddItem {
        fun onItemClick(position: String)
    }

    interface OnRemoveItem {
        fun onItemRemoveClick(position: String)
    }

}

