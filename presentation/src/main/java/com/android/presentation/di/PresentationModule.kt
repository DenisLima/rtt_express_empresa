package com.android.presentation.di

import com.android.presentation.features.list.AndroidJobListViewModel
import com.android.presentation.features.list.AndroidJobsAdapter
import com.android.presentation.features.main.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { AndroidJobsAdapter() }

    viewModel { MainViewModel() }

    viewModel { AndroidJobListViewModel(
        jobsUseCase = get()
    )
    }
}