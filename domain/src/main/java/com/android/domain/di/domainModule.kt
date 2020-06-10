package com.android.domain.di

import com.android.domain.features.jobslist.usecases.GetJobsUseCases
import com.android.domain.features.jobslist.usecases.GetJobsUseCasesImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetJobsUseCases> {
        GetJobsUseCasesImpl(
            repository = get()
        )
    }
}

val domainModule = listOf(useCaseModule)