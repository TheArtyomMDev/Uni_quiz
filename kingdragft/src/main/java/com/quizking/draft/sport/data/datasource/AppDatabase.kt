package com.quizking.draft.sport.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quizking.draft.sport.data.model.QuestionDto

@Database(entities = [QuestionDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao?
}