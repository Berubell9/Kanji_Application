package com.example.kanji.ui

import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.data.local.QuizResultEntity
import com.example.kanji.model.AppScreen
import com.example.kanji.model.GameMode

data class KanjiUiState(
    val screen: AppScreen = AppScreen.HOME,
    val mode: GameMode = GameMode.READING,
    val questions: List<KanjiEntity> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val selectedAnswer: String? = null,
    val options: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val latestResults: List<QuizResultEntity> = emptyList()
)