package com.android.domain.di

import com.android.domain.features.generalregister.GeneralRegisterUseCases
import com.android.domain.features.generalregister.GeneralRegisterUseCasesImpl
import com.android.domain.features.jobslist.usecases.GetJobsUseCases
import com.android.domain.features.jobslist.usecases.GetJobsUseCasesImpl
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.domain.features.loginregister.LoginRegisterUseCasesImpl
import com.android.domain.login.LoginUseCases
import com.android.domain.login.LoginUseCasesImpl
import org.koin.dsl.module

val useCaseModule = module {

    //General Example
    factory<GetJobsUseCases> {
        GetJobsUseCasesImpl(
            repository = get()
        )
    }

    //Login Register
    factory<LoginRegisterUseCases> {
        LoginRegisterUseCasesImpl(get())
    }

    //Login
    factory<LoginUseCases> {
        LoginUseCasesImpl(get())
    }

    //General Register
    factory<GeneralRegisterUseCases> {
        GeneralRegisterUseCasesImpl(get())
    }
}

val domainModule = listOf(useCaseModule)