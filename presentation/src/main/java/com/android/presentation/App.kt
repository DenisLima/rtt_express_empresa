package com.android.presentation

import android.app.Application
import com.android.data.di.dataModules
import com.android.domain.di.domainModule
import com.android.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}