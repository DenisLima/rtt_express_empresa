package com.android.data.mapper

import com.android.data.models.AndroidJobPayload
import com.android.data.models.JobsPayload
import com.android.domain.entities.AndroidJob

object AndroidJobMapper {

    fun map(payload: JobsPayload) = payload.jobsPayload.map { map(it) }

    private fun map(payload: AndroidJobPayload) = AndroidJob(
        title = payload.title,
        experienceTimeRequired = payload.requiredExperienceYears,
        native = payload.native,
        country = payload.country
    )
}