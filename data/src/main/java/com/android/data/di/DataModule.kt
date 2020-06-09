package com.android.data.di

import com.android.data.AndroidJobsRepositoryImpl
import com.android.domain.repository.AndroidJobsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AndroidJobsRepository> {
        AndroidJobsRepositoryImpl(
            jobsCacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)