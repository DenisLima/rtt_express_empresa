package com.android.data.features.jobslist.mapper

import com.android.data.features.jobslist.models.AndroidJobCache
import com.android.domain.features.jobslist.entities.AndroidJob

object AndroidJobCacheMapper {

    fun map(cacheData: List<AndroidJobCache>) = cacheData.map { map(it) }

    private fun map(cacheData: AndroidJobCache) = AndroidJob(
        title = cacheData.title,
        experienceTimeRequired = cacheData.requiredExperienceYears,
        native = cacheData.native,
        country = cacheData.country
    )

    fun mapJobsToCache(jobs: List<AndroidJob>) = jobs.map { map(it) }

    fun map(data: AndroidJob) = AndroidJobCache(
        title = data.title,
        requiredExperienceYears = data.experienceTimeRequired.toInt(),
        native = data.native,
        country = data.country
    )
}