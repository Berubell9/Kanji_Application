package com.example.kanji.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mode: String,
    val score: Int,
    val total: Int,
    val playedAt: Long = System.currentTimeMillis()
)