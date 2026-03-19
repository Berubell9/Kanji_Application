package com.example.kanji.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kanji.model.AppScreen
import com.example.kanji.model.GameMode
import com.example.kanji.ui.screens.HomeScreen
import com.example.kanji.ui.screens.QuizScreen
import com.example.kanji.ui.screens.ResultScreen

@Composable
fun KanjiQuizApp(
    viewModel: KanjiViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.screen) {
        AppScreen.HOME -> {
            HomeScreen(
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                latestResults = uiState.latestResults,
                onReadingClick = {
                    viewModel.startQuiz(GameMode.READING)
                },
                onMeaningClick = {
                    viewModel.startQuiz(GameMode.MEANING)
                }
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
                    selectedAnswer = uiState.selectedAnswer,
                    onAnswerClick = { answer ->
                        viewModel.selectAnswer(answer)
                    },
                    onNextClick = {
                        viewModel.goNext()
                    }
                )
            }
        }

        AppScreen.RESULT -> {
            ResultScreen(
                score = uiState.score,
                total = uiState.questions.size,
                onReplay = {
                    viewModel.replayQuiz()
                },
                onBackHome = {
                    viewModel.backHome()
                }
            )
        }
    }
}