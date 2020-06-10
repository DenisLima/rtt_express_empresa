package com.android.data.features.jobslist.source

import com.android.data.features.jobslist.api.ServerApi
import com.android.data.extension.mapRemoteErrors
import com.android.data.features.jobslist.models.JobsPayload
import com.android.domain.features.jobslist.response.ResultRemote

interface RemoteDataSource {
    suspend fun getJobs(): ResultRemote<JobsPayload>
}

class RemoteDataSourceImpl(
    private val serverApi: ServerApi
): RemoteDataSource {

    override suspend fun getJobs(): ResultRemote<JobsPayload> {
        return try {
            val jobsPayload = serverApi.fetchJobs()

            ResultRemote.Success(
                response = jobsPayload
            )
        } catch (throwable: Throwable) {
            throwable.mapRemoteErrors()
        }
    }
}