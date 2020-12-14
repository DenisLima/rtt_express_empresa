package com.android.presentation.di

import com.android.presentation.features.generalregister.GeneralRegisterViewModel
import com.android.presentation.features.jobslist.list.AndroidJobListViewModel
import com.android.presentation.features.jobslist.list.AndroidJobsAdapter
import com.android.presentation.features.jobslist.main.MainViewModel
import com.android.presentation.features.loadings.generateloadings.GenerateLoadingsFragmentViewModel
import com.android.presentation.features.loadings.generateloadings.form.GenerateLoadingsFormFragmentViewModel
import com.android.presentation.features.loginregister.LoginRegisterViewModel
import com.android.presentation.features.splash.SplashViewModel
import com.android.presentation.home.HomeViewModel
import com.android.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { AndroidJobsAdapter() }

    viewModel { MainViewModel() }

    viewModel { AndroidJobListViewModel(
        jobsUseCase = get()
    )
    }

    //Splash
    viewModel { SplashViewModel(get()) }

    //Login Register
    viewModel { LoginRegisterViewModel(get()) }

    //Login
    viewModel { LoginViewModel(get(), get()) }

    //General Register
    viewModel { GeneralRegisterViewModel(get()) }

    viewModel { HomeViewModel(get()) }

    //Loadings
    viewModel { GenerateLoadingsFragmentViewModel(get()) }

    viewModel { GenerateLoadingsFormFragmentViewModel(get()) }
}