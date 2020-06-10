package com.android.data.features.jobslist

import com.android.data.features.jobslist.mapper.AndroidJobCacheMapper
import com.android.data.features.jobslist.mapper.AndroidJobMapper
import com.android.data.features.jobslist.source.JobsCacheDataSource
import com.android.data.features.jobslist.source.RemoteDataSource
import com.android.domain.features.jobslist.entities.AndroidJob
import com.android.domain.features.jobslist.repository.AndroidJobsRepository
import com.android.domain.features.jobslist.response.ResultRemote
import com.android.domain.features.jobslist.response.ResultRequired
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidJobsRepositoryImpl(
    private val jobsCacheDataSource: JobsCacheDataSource,
    private val remoteDataSource: RemoteDataSource
): AndroidJobsRepository {

    override fun getJobs(): Flow<ResultRequired<List<AndroidJob>>> {
        return jobsCacheDataSource.getJobs()
            .map { cacheList ->
                val result = when {
                    cacheList.isEmpty() -> getJobsRemote()
                    else -> {
                        val jobs = AndroidJobCacheMapper.map(cacheList)
                        ResultRequired.Success(jobs)
                    }
                }

                result
            }
    }

    override fun add() {
        val androidJob = AndroidJob(
            title = "flow",
            experienceTimeRequired = (0..500).random(),
            native = true,
            country = "Braziil"
        )

        val cacheJob = AndroidJobCacheMapper.map(androidJob)
        jobsCacheDataSource.insertData(cacheJob)
    }

    private suspend fun getJobsRemote(): ResultRequired<List<AndroidJob>> {
        val resultRemote = remoteDataSource.getJobs()

        return when(resultRemote) {
            is ResultRemote.Success -> {
                val mappedList = AndroidJobMapper.map(resultRemote.response)
                val cacheList = AndroidJobCacheMapper.mapJobsToCache(mappedList)

                jobsCacheDataSource.updateData(cacheList)

                ResultRequired.Success(
                    result = mappedList
                )
            }
            is ResultRemote.ErrorResponse -> {
                ResultRequired.Error(resultRemote.throwable)
            }
        }
    }
}