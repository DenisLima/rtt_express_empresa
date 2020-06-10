package com.android.data.features.jobslist.api

import com.android.data.features.jobslist.models.JobsPayload
import retrofit2.http.GET

interface ServerApi {

    @GET("/android-jobs")
    suspend fun fetchJobs(): JobsPayload
}