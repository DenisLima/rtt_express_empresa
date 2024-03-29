package com.android.presentation.features.jobslist.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.features.jobslist.entities.AndroidJob
import com.android.presentation.R
import com.android.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_android_job.view.*

class AndroidJobsAdapter: RecyclerView.Adapter<AndroidJobsAdapter.ViewHolder>() {
    var jobs: List<AndroidJob> = listOf()

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.item_android_job)) {

        fun bind(androidJob: AndroidJob) = with(itemView) {
            txtTitle.text = androidJob.title
            txtCountry.text = androidJob.country
            txtYears.text = androidJob.experienceTimeRequired.toString()

            chkNative.isChecked = androidJob.native
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)
    override fun getItemCount(): Int = jobs.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(jobs[position])
}