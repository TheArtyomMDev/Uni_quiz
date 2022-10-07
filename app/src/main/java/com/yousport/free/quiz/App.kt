package com.yousport.free.quiz

import android.app.Application
import com.yousport.free.quiz.di.databaseModule
import com.yousport.free.quiz.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(viewModelsModule, databaseModule))
        }
    }
}