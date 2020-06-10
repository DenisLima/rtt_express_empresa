package com.android.data.features.jobslist.mapper

import com.android.data.features.jobslist.models.AndroidJobPayload
import com.android.data.features.jobslist.models.JobsPayload
import com.android.domain.features.jobslist.entities.AndroidJob

object AndroidJobMapper {

    fun map(payload: JobsPayload) = payload.jobsPayload.map { map(it) }

    private fun map(payload: AndroidJobPayload) = AndroidJob(
        title = payload.title,
        experienceTimeRequired = payload.requiredExperienceYears,
        native = payload.native,
        country = payload.country
    )
}