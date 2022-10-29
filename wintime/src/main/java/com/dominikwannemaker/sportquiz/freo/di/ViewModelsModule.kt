package com.dominikwannemaker.sportquiz.freo.di

import com.dominikwannemaker.sportquiz.freo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get(), get(), get())
    }
}