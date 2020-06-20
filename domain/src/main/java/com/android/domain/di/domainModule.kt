package com.android.domain.di

import com.android.domain.features.jobslist.usecases.GetJobsUseCases
import com.android.domain.features.jobslist.usecases.GetJobsUseCasesImpl
import com.android.domain.login.LoginUseCases
import com.android.domain.login.LoginUseCasesImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetJobsUseCases> {
        GetJobsUseCasesImpl(
            repository = get()
        )
    }

    factory<LoginUseCases> {
        LoginUseCasesImpl(get())
    }
}

val domainModule = listOf(useCaseModule)