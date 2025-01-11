package com.arturmaslov.lkz_droid.di

import com.arturmaslov.lkz_droid.viewmodel.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WordViewModel(get()) }
}