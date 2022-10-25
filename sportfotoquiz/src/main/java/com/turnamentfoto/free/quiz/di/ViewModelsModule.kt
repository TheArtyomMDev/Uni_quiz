package com.turnamentfoto.free.quiz.di

import com.turnamentfoto.free.quiz.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get(), get(), get())
    }
}