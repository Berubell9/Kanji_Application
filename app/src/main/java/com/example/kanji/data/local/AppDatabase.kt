package com.example.kanji.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        KanjiEntity::class,
        QuizResultEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kanjiDao(): KanjiDao
    abstract fun quizResultDao(): QuizResultDao
}