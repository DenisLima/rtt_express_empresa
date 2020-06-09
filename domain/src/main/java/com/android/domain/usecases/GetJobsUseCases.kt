package com.android.domain.usecases

import com.android.domain.models.AndroidJob
import com.android.domain.repository.AndroidJobsRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetJobsUseCases(
    private val repository: AndroidJobsRepository,
    private val scheduler: Scheduler
) {

    fun execute(forceUpdate: Boolean): Observable<List<AndroidJob>> {
        return repository.getJobs(forceUpdate)
            .subscribeOn(scheduler)
    }

}