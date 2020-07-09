package com.android.data.di

import android.content.Context
import com.android.data.features.jobslist.AndroidJobsRepositoryImpl
import com.android.data.features.general.BuildInfoHelper
import com.android.data.features.generalregister.GeneralRegisterRepositoryImpl
import com.android.data.features.generalregister.api.GeneralRegisterDataSource
import com.android.data.features.jobslist.api.ServerApi
import com.android.data.features.jobslist.source.RemoteDataSource
import com.android.data.features.jobslist.source.RemoteDataSourceImpl
import com.android.data.features.loginregister.LoginRegisterRepositoryImpl
import com.android.data.features.loginregister.api.LoginRegisterDataSource
import com.android.data.features.session.datasources.SessionLocalSource
import com.android.data.features.session.datasources.SessionLocalSourceImpl
import com.android.data.infra.AuthorizationInterceptor
import com.android.data.infra.ServiceFactory
import com.android.data.login.LoginRepositoryImpl
import com.android.data.login.api.LoginDataSource
import com.android.domain.features.generalregister.GeneralRegisterRepository
import com.android.domain.features.jobslist.repository.AndroidJobsRepository
import com.android.domain.features.loginregister.LoginRegisterRepository
import com.android.domain.login.LoginRepository
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val KOIN_IS_DEBUG = "isDebug"
const val KOIN_WEB_API_URL = "webApiUrl"
const val KOIN_SESSION_SHARED_PREFERENCES = "sharedPreferencesSession"
const val KOIN_SESSION_SHARED_PREFERENCES_C = "sharedPreferencesSession"
private const val SESSION_SHARED_PREFERENCES_NAME = "spAl"
const val KOIN_RX_SESSION_PREFERENCES = "rxSessionPreferences"
const val KOIN_OKHTTP = "okHttp"
const val KOIN_RETROFIT = "retrofit"

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

    single {
        ServiceFactory.createService(
            get(named(KOIN_RETROFIT)),
            ServerApi::class.java
        )
    }

    single {
        HttpLoggingInterceptor().apply {
            level =
                if (get(named(KOIN_IS_DEBUG))) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    single {
        AuthorizationInterceptor(get())
    }

    single(named(KOIN_OKHTTP)) {
        OkHttpClient.Builder()
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthorizationInterceptor>())
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(get<String>(named(KOIN_WEB_API_URL)))
            .client(get(named(KOIN_OKHTTP)))
            .build()
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(serverApi = get()) }

    factory {
        Gson()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
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
        SessionLocalSourceImpl(
            gson = get(),
            ctx = get<Context>()
        )
    }

    //Login Register
    single {
        ServiceFactory.createService(
            get(named(KOIN_RETROFIT)),
            LoginRegisterDataSource::class.java
        )
    }

    factory<LoginRegisterRepository> {
        LoginRegisterRepositoryImpl(get())
    }

    //Login
    single {
        ServiceFactory.createService(
            get(named(KOIN_RETROFIT)),
            LoginDataSource::class.java
        )
    }

    factory<LoginRepository>{
        LoginRepositoryImpl(get(), get())
    }

    //General Register
    single {
        ServiceFactory.createService(
            get(named(KOIN_RETROFIT)),
            GeneralRegisterDataSource::class.java
        )
    }

    factory<GeneralRegisterRepository> {
        GeneralRegisterRepositoryImpl(get(), get())
    }

}

val dataModules = listOf(repositoryModule, cacheDataModule)