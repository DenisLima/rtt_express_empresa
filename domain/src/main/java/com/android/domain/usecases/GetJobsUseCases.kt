package com.android.domain.usecases

import com.android.domain.entities.AndroidJob
import com.android.domain.repository.AndroidJobsRepository
import com.android.domain.response.ResultRequired
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.subscribeOn

interface GetJobsUseCases{

    fun getJobs(): Flow<ResultJobs>
    fun addJob()

    sealed class ResultJobs {
        data class Jobs(val list: List<AndroidJob>): ResultJobs()
        object NoJobs: ResultJobs()
        object Error: ResultJobs()
    }

}

class GetJobsUseCasesImpl(
    private val repository: AndroidJobsRepository
): GetJobsUseCases {

    override fun getJobs(): Flow<GetJobsUseCases.ResultJobs> {
        return repository.getJobs()
            .map {
                when(it) {
                    is ResultRequired.Success -> {
                        when {
                            it.result.isEmpty() -> GetJobsUseCases.ResultJobs.NoJobs
                            else -> GetJobsUseCases.ResultJobs.Jobs( it.result.reversed())
                        }
                    }
                    is ResultRequired.Error -> {
                        println(it.throwable.message)
                        GetJobsUseCases.ResultJobs.Error
                    }
                }
            }
    }

    override fun addJob() {
        repository.add()
    }
}