package com.android.data.di

import com.android.data.features.jobslist.AndroidJobsRepositoryImpl
import com.android.data.features.general.BuildInfoHelper
import com.android.data.features.loginregister.LoginRegisterRepositoryImpl
import com.android.data.login.LoginRepositoryImpl
import com.android.domain.features.jobslist.repository.AndroidJobsRepository
import com.android.domain.features.loginregister.LoginRegisterRepository
import com.android.domain.login.LoginRepository
import kotlinx.coroutines.channels.Channel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val KOIN_IS_DEBUG = "isDebug"
const val KOIN_WEB_API_URL = "webApiUrl"

val repositoryModule = module {
    factory<AndroidJobsRepository> {
        AndroidJobsRepositoryImpl(
            jobsCacheDataSource = get(),
            remoteDataSource = get()
        )
    }

    // ========= GENERAL SETTINGS

    single(named(KOIN_IS_DEBUG)) {
        get<BuildInfoHelper>().isDebug
    }

    single(named(KOIN_WEB_API_URL)) {
        get<BuildInfoHelper>().webApiUrl
    }

    single {
        BuildInfoHelper()
    }

    //Login Register
    factory<LoginRegisterRepository> {
        LoginRegisterRepositoryImpl(get())
    }

    //Login
    factory<LoginRepository>{
        LoginRepositoryImpl(get())
    }

}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)