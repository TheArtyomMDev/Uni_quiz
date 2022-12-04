package com.quizking.draft.sport.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.quizking.draft.sport.data.datasource.AppDatabase
import com.quizking.draft.sport.data.repository.QuestionRepository
import org.koin.dsl.module
import androidx.datastore.preferences.core.Preferences

var databaseModule = module {
    single {
        QuestionRepository(get())
    }

    fun provideDatastore(context: Context): DataStore<Preferences> {
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

    single {
        provideDatastore(get())
    }
}

val Context.dataStore by preferencesDataStore(name = "settings")
