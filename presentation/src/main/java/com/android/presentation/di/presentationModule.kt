package com.android.presentation.di

import com.android.presentation.features.generalregister.GeneralRegisterViewModel
import com.android.presentation.features.jobslist.list.AndroidJobListViewModel
import com.android.presentation.features.jobslist.list.AndroidJobsAdapter
import com.android.presentation.features.jobslist.main.MainViewModel
import com.android.presentation.features.loginregister.LoginRegisterViewModel
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

    //Login Register
    viewModel { LoginRegisterViewModel(get()) }

    //Login
    viewModel { LoginViewModel(get()) }

    //General Register
    viewModel { GeneralRegisterViewModel(get()) }
}