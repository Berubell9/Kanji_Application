package com.example.kanji.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.data.repository.KanjiRepository
import com.example.kanji.model.AppScreen
import com.example.kanji.model.GameMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KanjiViewModel(
    private val repository: KanjiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(KanjiUiState())
    val uiState: StateFlow<KanjiUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedIfEmpty()

            repository.observeLatestResults().collect { results ->
                _uiState.update { currentState ->
                    currentState.copy(latestResults = results)
                }
            }
        }
    }

    fun startQuiz(mode: GameMode) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                val questions = repository.getQuizQuestions()

                if (questions.size < 4) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = "ต้องมีข้อมูลอย่างน้อย 4 ข้อในฐานข้อมูล"
                        )
                    }
                    return@launch
                }

                _uiState.update { currentState ->
                    currentState.copy(
                        screen = AppScreen.QUIZ,
                        mode = mode,
                        questions = questions,
                        currentIndex = 0,
                        score = 0,
                        selectedAnswer = null,
                        options = buildOptions(
                            item = questions.first(),
                            allQuestions = questions,
                            mode = mode
                        ),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "เกิดข้อผิดพลาด"
                    )
                }
            }
        }
    }

    fun selectAnswer(answer: String) {
        val state = _uiState.value
        if (state.selectedAnswer != null) return
        if (state.questions.isEmpty()) return

        val currentQuestion = state.questions[state.currentIndex]
        val correctAnswer = getCorrectAnswer(currentQuestion, state.mode)

        _uiState.update { currentState ->
            currentState.copy(
                selectedAnswer = answer,
                score = if (answer == correctAnswer) currentState.score + 1 else currentState.score
            )
        }
    }

    fun goNext() {
        val state = _uiState.value
        if (state.questions.isEmpty()) return

        if (state.currentIndex == state.questions.lastIndex) {
            viewModelScope.launch {
                repository.saveResult(
                    mode = state.mode.name,
                    score = state.score,
                    total = state.questions.size
                )

                _uiState.update { currentState ->
                    currentState.copy(screen = AppScreen.RESULT)
                }
            }
        } else {
            val nextIndex = state.currentIndex + 1
            val nextQuestion = state.questions[nextIndex]

            _uiState.update { currentState ->
                currentState.copy(
                    currentIndex = nextIndex,
                    selectedAnswer = null,
                    options = buildOptions(
                        item = nextQuestion,
                        allQuestions = state.questions,
                        mode = state.mode
                    )
                )
            }
        }
    }

    fun replayQuiz() {
        startQuiz(_uiState.value.mode)
    }

    fun backHome() {
        _uiState.update { currentState ->
            currentState.copy(
                screen = AppScreen.HOME,
                selectedAnswer = null,
                errorMessage = null
            )
        }
    }

    private fun getCorrectAnswer(item: KanjiEntity, mode: GameMode): String {
        return when (mode) {
            GameMode.READING -> item.reading
            GameMode.MEANING -> item.meaning
        }
    }

    private fun buildOptions(
        item: KanjiEntity,
        allQuestions: List<KanjiEntity>,
        mode: GameMode
    ): List<String> {
        val correct = getCorrectAnswer(item, mode)

        val allChoices = when (mode) {
            GameMode.READING -> allQuestions.map { question -> question.reading }
            GameMode.MEANING -> allQuestions.map { question -> question.meaning }
        }

        val wrongChoices = allChoices
            .filter { choice -> choice != correct }
            .distinct()
            .shuffled()
            .take(3)

        return (listOf(correct) + wrongChoices).shuffled()
    }

    companion object {
        fun provideFactory(repository: KanjiRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(KanjiViewModel::class.java)) {
                        return KanjiViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}