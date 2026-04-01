package com.example.kanji.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kanji.model.AppScreen
import com.example.kanji.ui.screens.CategoryScreen
import com.example.kanji.ui.screens.ModeScreen
import com.example.kanji.ui.screens.NameInputScreen
import com.example.kanji.ui.screens.QuizScreen
import com.example.kanji.ui.screens.ResultScreen

@Composable
fun KanjiQuizApp(
    viewModel: KanjiViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.screen) {
        AppScreen.NAME_INPUT -> {
            NameInputScreen(
                playerName = uiState.playerName,
                errorMessage = uiState.errorMessage,
                onNameChange = { name -> viewModel.updatePlayerName(name) },
                onStartClick = { viewModel.goToCategoryScreen() }
            )
        }

        AppScreen.CATEGORY -> {
            CategoryScreen(
                playerName = uiState.playerName,
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = { category -> viewModel.selectCategory(category) },
                onBackClick = { viewModel.goBackToNameInput() },
                onNextClick = { viewModel.goToModeScreen() }
            )
        }

        AppScreen.MODE -> {
            ModeScreen(
                playerName = uiState.playerName,
                selectedCategory = uiState.selectedCategory,
                selectedMode = uiState.selectedMode,
                onModeSelected = { mode -> viewModel.selectMode(mode) },
                onBackClick = { viewModel.goBackToCategory() },
                onNextClick = { viewModel.startQuizFromSelectedMode() }
            )
        }

        AppScreen.QUIZ -> {
            if (uiState.questions.isNotEmpty()) {
                val currentQuestion = uiState.questions[uiState.currentIndex]

                QuizScreen(
                    mode = uiState.mode,
                    question = currentQuestion,
                    options = uiState.options,
                    questionNumber = uiState.currentIndex + 1,
                    totalQuestions = uiState.questions.size,
                    score = uiState.score,
                    pendingAnswer = uiState.pendingAnswer,
                    selectedAnswer = uiState.selectedAnswer,
                    onAnswerClick = { answer -> viewModel.selectAnswer(answer) },
                    onConfirmClick = { viewModel.confirmAnswer() },
                    onNextClick = { viewModel.goNext() }
                )
            }
        }

        AppScreen.RESULT -> {
            ResultScreen(
                score = uiState.score,
                total = uiState.questions.size,
                onReplay = { viewModel.replayQuiz() },
                onBackHome = { viewModel.backHome() }
            )
        }
    }
}