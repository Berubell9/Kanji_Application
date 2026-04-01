package com.example.kanji.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kanji")
data class KanjiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kanji: String,
    val reading: String,
    val meaning: String,
    val imageRes: Int,
    val category: String
)