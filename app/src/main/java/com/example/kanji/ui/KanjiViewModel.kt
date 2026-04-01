package com.example.kanji.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.data.repository.KanjiRepository
import com.example.kanji.model.AppScreen
import com.example.kanji.model.GameMode
import com.example.kanji.model.PracticeCategory
import com.example.kanji.model.PracticeMode
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

    fun updatePlayerName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                playerName = name,
                errorMessage = null
            )
        }
    }

    fun goToCategoryScreen() {
        val name = _uiState.value.playerName.trim()
        if (name.isBlank()) {
            _uiState.update { currentState ->
                currentState.copy(errorMessage = "กรุณากรอกชื่อเล่นก่อนเริ่ม")
            }
            return
        }

        _uiState.update { currentState ->
            currentState.copy(
                screen = AppScreen.CATEGORY,
                errorMessage = null
            )
        }
    }

    fun selectCategory(category: PracticeCategory) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCategory = category,
                errorMessage = null
            )
        }
    }

    fun goToModeScreen() {
        _uiState.update { currentState ->
            currentState.copy(
                screen = AppScreen.MODE,
                selectedMode = null,
                errorMessage = null
            )
        }
    }

    fun selectMode(mode: PracticeMode) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMode = mode,
                errorMessage = null
            )
        }
    }

    fun startQuizFromSelectedMode() {
        when (_uiState.value.selectedMode) {
            PracticeMode.READING -> startQuiz(GameMode.READING)
            PracticeMode.MEANING -> startQuiz(GameMode.MEANING)
            null -> {
                _uiState.update { currentState ->
                    currentState.copy(errorMessage = "กรุณาเลือกโหมดก่อน")
                }
            }
        }
    }

    fun goBackToNameInput() {
        _uiState.update { currentState ->
            currentState.copy(
                screen = AppScreen.NAME_INPUT,
                errorMessage = null
            )
        }
    }

    fun goBackToCategory() {
        _uiState.update { currentState ->
            currentState.copy(
                screen = AppScreen.CATEGORY,
                selectedMode = null,
                errorMessage = null
            )
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
                val state = _uiState.value

                // ดึงจากหมวดที่เลือก ถ้าเลือก ALL ก็จะได้จากทั้งหมด
                val randomItems = repository.getQuizQuestions(state.selectedCategory)

                // จำกัดให้เล่นแค่ 10 ข้อ
                val questions = randomItems.take(10)

                if (questions.isEmpty()) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = "หมวดหมู่นี้ยังไม่มีข้อมูลสำหรับฝึก"
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
                        pendingAnswer = null,
                        selectedAnswer = null,
                        options = buildOptions(
                            item = questions.first(),
                            optionPool = questions,
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

        _uiState.update { currentState ->
            currentState.copy(
                pendingAnswer = answer
            )
        }
    }

    fun confirmAnswer() {
        val state = _uiState.value
        if (state.questions.isEmpty()) return
        if (state.pendingAnswer == null) return
        if (state.selectedAnswer != null) return

        val currentQuestion = state.questions[state.currentIndex]
        val correctAnswer = getCorrectAnswer(currentQuestion, state.mode)

        _uiState.update { currentState ->
            currentState.copy(
                selectedAnswer = state.pendingAnswer,
                score = if (state.pendingAnswer == correctAnswer) {
                    currentState.score + 1
                } else {
                    currentState.score
                }
            )
        }
    }

    fun goNext() {
        val state = _uiState.value
        if (state.questions.isEmpty()) return
        if (state.selectedAnswer == null) return

        if (state.currentIndex == state.questions.lastIndex) {
            viewModelScope.launch {
                repository.saveResult(
                    mode = "${state.mode.name}_${state.selectedCategory.name}",
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
                    pendingAnswer = null,
                    selectedAnswer = null,
                    options = buildOptions(
                        item = nextQuestion,
                        optionPool = state.questions,
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
                screen = AppScreen.NAME_INPUT,
                selectedCategory = PracticeCategory.ALL,
                selectedMode = null,
                pendingAnswer = null,
                selectedAnswer = null,
                errorMessage = null
            )
        }
    }

    private fun getCorrectAnswer(item: KanjiEntity, mode: GameMode): String {
        return when (mode) {
            GameMode.READING -> item.reading
            GameMode.MEANING -> item.meaning
        }.trim()
    }

    private fun buildOptions(
        item: KanjiEntity,
        optionPool: List<KanjiEntity>,
        mode: GameMode
    ): List<String> {
        val correct = getCorrectAnswer(item, mode)

        val allChoices = when (mode) {
            GameMode.READING -> optionPool.map { it.reading.trim() }
            GameMode.MEANING -> optionPool.map { it.meaning.trim() }
        }

        val wrongChoices = allChoices
            .filter { it.isNotBlank() && it != correct }
            .distinct()
            .shuffled()
            .take(3)

        return (listOf(correct) + wrongChoices)
            .distinct()
            .take(4)
            .shuffled()
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