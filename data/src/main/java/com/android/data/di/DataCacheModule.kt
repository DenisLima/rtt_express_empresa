package com.android.data.di

import com.android.data.database.JobsDataBase
import com.android.data.source.JobsCacheDataSource
import com.android.data.source.JobsCacheSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { JobsDataBase.createDataBase(androidContext()) }
    factory<JobsCacheDataSource> { JobsCacheSourceImpl(jobsDao = get()) }
}