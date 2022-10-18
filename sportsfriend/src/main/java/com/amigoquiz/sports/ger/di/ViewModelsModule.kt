package com.amigoquiz.sports.ger.di

import com.amigoquiz.sports.ger.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get(), get(), get())
    }
}