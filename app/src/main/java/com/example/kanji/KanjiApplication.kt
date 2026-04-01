package com.example.kanji

import android.app.Application
import androidx.room.Room
import com.example.kanji.data.local.AppDatabase
import com.example.kanji.data.repository.KanjiRepository

class KanjiApplication : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "kanji_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val repository: KanjiRepository by lazy {
        KanjiRepository(
            kanjiDao = database.kanjiDao(),
            quizResultDao = database.quizResultDao()
        )
    }
}