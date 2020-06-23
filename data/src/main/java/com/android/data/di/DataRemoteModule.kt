package com.android.data.di


import com.android.data.features.jobslist.api.ServerApi
import com.android.data.features.jobslist.source.RemoteDataSource
import com.android.data.features.jobslist.source.RemoteDataSourceImpl
import com.android.data.features.loginregister.api.LoginRegisterDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { providesOkHttpClient() }
    single {
        createWebService<ServerApi>(
            okHttpClient = get(),
            url = get(named(KOIN_WEB_API_URL))
        )
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(serverApi = get()) }

    //Login Register
    single {
        createWebService<LoginRegisterDataSource>(
            okHttpClient = get(),
            url = get(named(KOIN_WEB_API_URL))
        )
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}