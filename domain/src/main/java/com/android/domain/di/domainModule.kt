package com.android.domain.di

import com.android.domain.features.jobslist.usecases.GetJobsUseCases
import com.android.domain.features.jobslist.usecases.GetJobsUseCasesImpl
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.domain.features.loginregister.LoginRegisterUseCasesImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetJobsUseCases> {
        GetJobsUseCasesImpl(
            repository = get()
        )
    }

    factory<LoginRegisterUseCases> {
        LoginRegisterUseCasesImpl(get())
    }
}

val domainModule = listOf(useCaseModule)