package com.android.data.api

import com.android.data.models.JobsPayload
import io.reactivex.Single
import retrofit2.http.GET

interface ServerApi {

    @GET("/android-jobs")
    suspend fun fetchJobs(): JobsPayload
}