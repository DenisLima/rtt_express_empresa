package com.android.domain.features.jobslist.repository

import com.android.domain.features.jobslist.entities.AndroidJob
import com.android.domain.features.jobslist.response.ResultRequired
import kotlinx.coroutines.flow.Flow

interface AndroidJobsRepository {
    fun getJobs(): Flow<ResultRequired<List<AndroidJob>>>
    fun add()
}