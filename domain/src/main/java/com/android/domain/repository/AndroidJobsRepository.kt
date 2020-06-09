package com.android.domain.repository

import com.android.domain.models.AndroidJob
import io.reactivex.Observable

interface AndroidJobsRepository {
    fun getJobs(forceUpdate: Boolean): Observable<List<AndroidJob>>
}