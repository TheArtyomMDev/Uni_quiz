package com.yousport.free.quiz.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.yousport.free.quiz.data.datasource.AppDatabase
import com.yousport.free.quiz.data.repository.QuestionRepository
import org.koin.dsl.module
import java.util.prefs.Preferences
import kotlin.properties.ReadOnlyProperty

var databaseModule = module {
    single {
        QuestionRepository(get())
    }



    fun provideDatastore(context: Context): ReadOnlyProperty<Context, DataStore<androidx.datastore.preferences.core.Preferences>> {
        return context.dataStore
    }

    fun provideQuestionsDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .createFromAsset("databases/database.db")
            .allowMainThreadQueries()
            .build()
    }

    single {
        provideQuestionsDatabase(get())
    }
}

val Context.dataStore: ReadOnlyProperty<Context, DataStore<androidx.datastore.preferences.core.Preferences>>
    get() = preferencesDataStore(name = "settings")
