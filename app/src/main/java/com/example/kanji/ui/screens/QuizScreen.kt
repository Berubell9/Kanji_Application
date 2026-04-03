package com.example.kanji.ui.screens

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.model.GameMode
import com.example.kanji.util.ImageMapper
import kotlinx.coroutines.delay

private val BgWhite = Color(0xFFFFFFFF)
private val QuestionBg = Color(0xFFF3EAFE)

private val ChoiceRed = Color(0xFFE21B3C)
private val ChoiceBlue = Color(0xFF1368CE)
private val ChoiceYellow = Color(0xFFD89E00)
private val ChoiceGreen = Color(0xFF26890C)

private val CorrectColor = Color(0xFF63C132)
private val WrongColor = Color(0xFFFF3B5C)
private val WrongMuted = Color(0xFFA95A5A)

private val ScreenPadding = 16.dp
private val OptionSpacing = 10.dp
private const val AUTO_NEXT_DELAY = 1300L

@Suppress("UNUSED_PARAMETER")
@Composable
fun QuizScreen(
    playerName: String,
    categoryText: String,
    mode: GameMode,
    question: KanjiEntity,
    options: List<String>,
    questionNumber: Int,
    totalQuestions: Int,
    score: Int,
    pendingAnswer: String?,
    selectedAnswer: String?,
    onAnswerClick: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val correctAnswer = when (mode) {
        GameMode.READING -> question.reading
        GameMode.MEANING -> question.meaning
    }.trim()

    val displayOptions = remember(options, correctAnswer) {
        val cleaned = options
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()

        if (correctAnswer in cleaned) {
            cleaned
        } else {
            (cleaned + correctAnswer).distinct()
        }
    }

    val isAnswered = selectedAnswer != null
    val isCorrect = selectedAnswer?.trim() == correctAnswer

    LaunchedEffect(selectedAnswer, questionNumber) {
        if (selectedAnswer != null) {
            delay(AUTO_NEXT_DELAY)
            onNextClick()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(horizontal = ScreenPadding, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ผู้เล่น: $playerName",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF3A3151)
                )

                Text(
                    text = "หมวด: $categoryText",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF5C5470)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ข้อ $questionNumber / $totalQuestions",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C5470)
                )

                Text(
                    text = "คะแนน $score",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C5470)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            SegmentedProgress(
                current = questionNumber,
                total = totalQuestions,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.56f)
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xFFE7E7E7)),
                    shadowElevation = 3.dp
                ) {
                    Image(
                        painter = painterResource(id = ImageMapper.getImageRes(question.imageName)),
                        contentDescription = question.meaning,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = isAnswered,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    ResultBanner(isCorrect = isCorrect)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = QuestionBg
            ) {
                Text(
                    text = question.kanji,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF3A3151),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val optionColors = listOf(
                    ChoiceRed,
                    ChoiceBlue,
                    ChoiceYellow,
                    ChoiceGreen
                )

                val rowHeight = (maxHeight - OptionSpacing) / 2

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(OptionSpacing)
                ) {
                    displayOptions.chunked(2).forEachIndexed { rowIndex, rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(rowHeight),
                            horizontalArrangement = Arrangement.spacedBy(OptionSpacing)
                        ) {
                            rowItems.forEachIndexed { colIndex, option ->
                                val absoluteIndex = rowIndex * 2 + colIndex
                                val baseColor = optionColors.getOrElse(absoluteIndex) { ChoiceBlue }

                                QuizOptionButton(
                                    text = option,
                                    baseColor = baseColor,
                                    marker = optionMarker(absoluteIndex),
                                    state = resolveOptionState(
                                        option = option,
                                        correctAnswer = correctAnswer,
                                        selectedAnswer = selectedAnswer
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight(),
                                    onClick = {
                                        if (selectedAnswer == null) {
                                            onAnswerClick(option)
                                            onConfirmClick()
                                        }
                                    }
                                )
                            }

                            if (rowItems.size == 1) {
                                Spacer(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SegmentedProgress(
    current: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(total.coerceAtLeast(1)) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(5.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(
                        if (index < current) Color(0xFF1F1F1F) else Color(0xFFE3E3E3)
                    )
            )
        }
    }
}

@Composable
private fun QuizOptionButton(
    text: String,
    baseColor: Color,
    marker: String,
    state: OptionVisualState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val backgroundColor = when (state) {
        OptionVisualState.NORMAL -> baseColor
        OptionVisualState.CORRECT -> CorrectColor
        OptionVisualState.WRONG_SELECTED -> WrongColor
        OptionVisualState.WRONG_OTHER -> WrongMuted
    }

    val badgeText = when (state) {
        OptionVisualState.CORRECT -> "✓"
        OptionVisualState.WRONG_SELECTED,
        OptionVisualState.WRONG_OTHER -> "✕"
        OptionVisualState.NORMAL -> marker
    }

    val textColor = when (state) {
        OptionVisualState.WRONG_OTHER -> Color.White.copy(alpha = 0.72f)
        else -> Color.White
    }

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                enabled = state == OptionVisualState.NORMAL,
                onClick = onClick
            ),
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.12f)),
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.95f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeText,
                    color = backgroundColor,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 14.dp),
                color = textColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun ResultBanner(isCorrect: Boolean) {
    val bannerColor = if (isCorrect) CorrectColor else WrongColor
    val text = if (isCorrect) "✓ ถูกต้อง" else "✕ ไม่ถูกต้อง"

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { rotationZ = -4f },
        color = bannerColor,
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

private fun resolveOptionState(
    option: String,
    correctAnswer: String,
    selectedAnswer: String?
): OptionVisualState {
    if (selectedAnswer == null) return OptionVisualState.NORMAL

    val normalizedOption = option.trim()
    val normalizedCorrect = correctAnswer.trim()
    val normalizedSelected = selectedAnswer.trim()

    return when {
        normalizedOption == normalizedCorrect -> OptionVisualState.CORRECT
        normalizedOption == normalizedSelected -> OptionVisualState.WRONG_SELECTED
        else -> OptionVisualState.WRONG_OTHER
    }
}

private fun optionMarker(index: Int): String {
    return when (index) {
        0 -> "▲"
        1 -> "◆"
        2 -> "●"
        else -> "■"
    }
}

private enum class OptionVisualState {
    NORMAL,
    CORRECT,
    WRONG_SELECTED,
    WRONG_OTHER
}