package com.yousport.free.quiz.di

import com.yousport.free.quiz.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelsModule = module {
    viewModel {
        MainViewModel(get())
    }
}