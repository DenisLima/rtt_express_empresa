package com.android.domain.repository

import com.android.domain.entities.AndroidJob
import com.android.domain.response.ResultRequired
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface AndroidJobsRepository {
    fun getJobs(): Flow<ResultRequired<List<AndroidJob>>>
    fun add()
}