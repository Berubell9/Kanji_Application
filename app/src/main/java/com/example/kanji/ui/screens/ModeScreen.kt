package com.example.kanji.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kanji.model.PracticeCategory
import com.example.kanji.model.PracticeMode
import com.example.kanji.ui.theme.TextSecondary

@Composable
fun ModeScreen(
    playerName: String,
    selectedCategory: PracticeCategory,
    selectedMode: PracticeMode?,
    onModeSelected: (PracticeMode) -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 12.dp)
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "เลือกโหมด",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4A3A3A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "ผู้เล่น: $playerName",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "หมวดหมู่: ${selectedCategory.displayName}",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(18.dp))

            ModeSelectableCard(
                title = "ทายการอ่าน",
                accentText = "あ",
                gradientColors = listOf(
                    Color(0xFF180022),
                    Color(0xFF3E0D6B),
                    Color(0xFF7A3FD3)
                ),
                selected = selectedMode == PracticeMode.READING,
                onClick = { onModeSelected(PracticeMode.READING) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ModeSelectableCard(
                title = "ทายความหมาย",
                accentText = "文",
                gradientColors = listOf(
                    Color(0xFF5B000A),
                    Color(0xFFA20D2A),
                    Color(0xFFE65A7A)
                ),
                selected = selectedMode == PracticeMode.MEANING,
                onClick = { onModeSelected(PracticeMode.MEANING) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "โหมดที่เลือก: ${selectedMode?.displayName ?: "-"}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF2A0B2)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD9D2E0),
                        contentColor = Color(0xFF7A6F7A)
                    )
                ) {
                    Text(
                        text = "ย้อนกลับ",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Button(
                    onClick = onNextClick,
                    enabled = selectedMode != null,
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF3A8B9),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFF3A8B9).copy(alpha = 0.5f),
                        disabledContentColor = Color.White.copy(alpha = 0.8f)
                    )
                ) {
                    Text(
                        text = "ถัดไป",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun ModeSelectableCard(
    title: String,
    accentText: String,
    gradientColors: List<Color>,
    selected: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(114.dp)
            .clip(shape)
            .border(
                width = if (selected) 3.dp else 0.dp,
                color = if (selected) Color.White else Color.Transparent,
                shape = shape
            )
            .clickable { onClick() },
        shape = shape,
        shadowElevation = if (selected) 8.dp else 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(gradientColors)
                )
        ) {
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .align(Alignment.TopCenter)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.10f))
            )

            Box(
                modifier = Modifier
                    .size(95.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 28.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.10f))
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.68f)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.20f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 18.dp, bottom = 14.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 18.dp)
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = accentText,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            if (selected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.95f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "เลือกแล้ว",
                        color = Color(0xFF333333),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}