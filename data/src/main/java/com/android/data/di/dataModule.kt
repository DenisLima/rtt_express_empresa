package com.android.data.di

import android.content.Context
import com.android.data.features.jobslist.AndroidJobsRepositoryImpl
import com.android.data.features.general.BuildInfoHelper
import com.android.data.features.generalregister.GeneralRegisterRepositoryImpl
import com.android.data.features.loginregister.LoginRegisterRepositoryImpl
import com.android.data.login.LoginRepositoryImpl
import com.android.data.session.SessionLocalSource
import com.android.data.session.SessionLocalSourceImp
import com.android.domain.features.generalregister.GeneralRegisterRepository
import com.android.domain.features.jobslist.repository.AndroidJobsRepository
import com.android.domain.features.loginregister.LoginRegisterRepository
import com.android.domain.login.LoginRepository
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.android.domain.features.jobslist.repository.AndroidJobsRepository
import com.android.domain.features.loginregister.LoginRegisterRepository
import com.android.domain.login.LoginRepository
import kotlinx.coroutines.channels.Channel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val KOIN_IS_DEBUG = "isDebug"
const val KOIN_WEB_API_URL = "webApiUrl"
const val KOIN_SESSION_SHARED_PREFERENCES = "sharedPreferencesSession"
private const val SESSION_SHARED_PREFERENCES_NAME = "spAl"
const val KOIN_RX_SESSION_PREFERENCES = "rxSessionPreferences"

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

    // ========== SHARED PREFERENCES SESSION
    single(named(KOIN_SESSION_SHARED_PREFERENCES)) {
        androidApplication()
            .getSharedPreferences(SESSION_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    single(named(KOIN_RX_SESSION_PREFERENCES)) {
        RxSharedPreferences.create(get(named(KOIN_SESSION_SHARED_PREFERENCES)))
    }

    single<SessionLocalSource> {
        SessionLocalSourceImp(
            sessionPreferences = get(named(KOIN_RX_SESSION_PREFERENCES))
        )
    }

    //Login Register
    factory<LoginRegisterRepository> {
        LoginRegisterRepositoryImpl(get())
    }

    //Login
    factory<LoginRepository>{
        LoginRepositoryImpl(get())
    }

    //General Register
    factory<GeneralRegisterRepository> {
        GeneralRegisterRepositoryImpl(get(), get())
    }

}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)