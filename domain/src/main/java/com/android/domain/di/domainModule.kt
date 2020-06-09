package com.android.domain.di

import com.android.domain.usecases.GetJobsUseCases
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

val useCaseModule = module {

    factory {
        GetJobsUseCases(
            repository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)