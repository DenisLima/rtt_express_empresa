package com.android.data.source

import com.android.data.api.ServerApi
import com.android.data.extension.mapRemoteErrors
import com.android.data.models.JobsPayload
import com.android.domain.response.ResultRemote

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