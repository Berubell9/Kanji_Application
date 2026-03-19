package com.example.kanji.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.model.GameMode

@Composable
fun QuizScreen(
    mode: GameMode,
    question: KanjiEntity,
    options: List<String>,
    questionNumber: Int,
    totalQuestions: Int,
    score: Int,
    selectedAnswer: String?,
    onAnswerClick: (String) -> Unit,
    onNextClick: () -> Unit
) {
    val correctAnswer = when (mode) {
        GameMode.READING -> question.reading
        GameMode.MEANING -> question.meaning
    }

    val questionText = when (mode) {
        GameMode.READING -> "รูปนี้อ่านว่าอะไร"
        GameMode.MEANING -> "รูปนี้แปลว่าอะไร"
    }

    val progressValue = if (totalQuestions > 0) {
        questionNumber.toFloat() / totalQuestions.toFloat()
    } else {
        0f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ข้อ $questionNumber / $totalQuestions",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "คะแนน $score",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { progressValue },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = questionText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.72f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = question.imageRes),
                    contentDescription = question.meaning,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "คันจิ: ${question.kanji}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        options.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = spacedBy(12.dp)
            ) {
                rowItems.forEach { option ->
                    Box(modifier = Modifier.weight(1f)) {
                        ChoiceButton(
                            text = option,
                            isCorrect = option == correctAnswer,
                            isSelected = selectedAnswer == option,
                            enabled = selectedAnswer == null,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onAnswerClick(option) }
                        )
                    }
                }

                if (rowItems.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (selectedAnswer != null) {
            val isCorrect = selectedAnswer == correctAnswer

            Text(
                text = if (isCorrect) {
                    "ถูกต้อง!"
                } else {
                    "ยังไม่ถูก คำตอบที่ถูกคือ $correctAnswer"
                },
                color = if (isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF58CC02)
                )
            ) {
                Text("ข้อถัดไป")
            }
        }
    }
}

@Composable
private fun ChoiceButton(
    text: String,
    isCorrect: Boolean,
    isSelected: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bgColor = when {
        !enabled && isCorrect -> Color(0xFFD8F5D0)
        !enabled && isSelected -> Color(0xFFFFDAD6)
        else -> Color.White
    }

    val borderColor = when {
        !enabled && isCorrect -> Color(0xFF4CAF50)
        !enabled && isSelected -> Color(0xFFE53935)
        else -> Color(0xFFDDDDDD)
    }

    Surface(
        modifier = modifier
            .height(82.dp)
            .sizeIn(minWidth = 0.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(2.dp, borderColor, RoundedCornerShape(18.dp))
            .clickable(enabled = enabled, onClick = onClick),
        color = bgColor,
        shape = RoundedCornerShape(18.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}