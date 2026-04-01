package com.example.kanji.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.model.GameMode
import com.example.kanji.ui.theme.CorrectBg
import com.example.kanji.ui.theme.CorrectGreen
import com.example.kanji.ui.theme.GreenLight
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.TextSecondary
import com.example.kanji.ui.theme.WrongBg
import com.example.kanji.ui.theme.WrongRed

@Composable
fun QuizScreen(
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

    val titleText = when (mode) {
        GameMode.READING -> "เลือกคำอ่านที่ถูกต้อง"
        GameMode.MEANING -> "เลือกความหมายที่ถูกต้อง"
    }

    val progressValue = if (totalQuestions > 0) {
        questionNumber.toFloat() / totalQuestions.toFloat()
    } else {
        0f
    }

    val displayOptions = options.distinct().take(4)
    val isLastQuestion = questionNumber == totalQuestions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = { progressValue },
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(99.dp)),
                color = GreenPrimary,
                trackColor = GreenLight
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "$questionNumber/$totalQuestions",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = titleText,
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(18.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 1.dp,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(22.dp),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Image(
                        painter = painterResource(id = question.imageRes),
                        contentDescription = question.meaning,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = question.kanji,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            val spacing = 14.dp
            val cardWidth = (maxWidth - spacing) / 2

            displayOptions.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    rowItems.forEach { option ->
                        OptionCard(
                            text = option,
                            isCorrect = option.trim() == correctAnswer,
                            isSelected = if (selectedAnswer != null) {
                                selectedAnswer == option
                            } else {
                                pendingAnswer == option
                            },
                            isConfirmed = selectedAnswer != null,
                            modifier = Modifier.width(cardWidth),
                            onClick = { onAnswerClick(option) }
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.width(cardWidth))
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }
        }

        if (selectedAnswer == null) {
            Button(
                onClick = onConfirmClick,
                enabled = pendingAnswer != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    disabledContainerColor = GreenPrimary.copy(alpha = 0.45f)
                )
            ) {
                Text(
                    text = "ยืนยันคำตอบ",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }

    if (selectedAnswer != null) {
        val isCorrect = selectedAnswer.trim() == correctAnswer

        AlertDialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            containerColor = if (isCorrect) {
                CorrectBg
            } else {
                WrongBg
            },
            title = {
                Text(
                    text = if (isCorrect) "ตอบถูก!" else "ตอบผิด",
                    color = if (isCorrect) CorrectGreen else WrongRed,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isCorrect) {
                            "เก่งมาก คำตอบของคุณถูกต้อง"
                        } else {
                            "คำตอบที่ถูกคือ $correctAnswer"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "คะแนนปัจจุบัน: $score คะแนน",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "ทำไปแล้ว $questionNumber / $totalQuestions ข้อ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onNextClick) {
                    Text(
                        text = if (isLastQuestion) "ดูผลลัพธ์" else "ข้อถัดไป",
                        fontWeight = FontWeight.Bold,
                        color = GreenPrimary
                    )
                }
            }
        )
    }
}

@Composable
private fun OptionCard(
    text: String,
    isCorrect: Boolean,
    isSelected: Boolean,
    isConfirmed: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bgColor = when {
        isConfirmed && isCorrect -> CorrectBg
        isConfirmed && isSelected && !isCorrect -> WrongBg
        else -> MaterialTheme.colorScheme.surface
    }

    val borderColor = when {
        isConfirmed && isCorrect -> CorrectGreen
        isConfirmed && isSelected && !isCorrect -> WrongRed
        !isConfirmed && isSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.18f)
    }

    Surface(
        modifier = modifier
            .height(112.dp)
            .sizeIn(minWidth = 0.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(18.dp))
            .clickable(enabled = !isConfirmed, onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = bgColor,
        tonalElevation = 1.dp,
        shadowElevation = 3.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}