package com.dominikwannemaker.sportquiz.freo.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dominikwannemaker.sportquiz.freo.data.model.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao?
}