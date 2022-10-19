package com.ferstsport.freequiz.rusaqer.di

import com.ferstsport.freequiz.rusaqer.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get(), get(), get())
    }
}