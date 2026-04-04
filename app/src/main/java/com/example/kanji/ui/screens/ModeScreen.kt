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
import com.example.kanji.ui.theme.BackButtonBg
import com.example.kanji.ui.theme.BackButtonText
import com.example.kanji.ui.theme.BgWhite
import com.example.kanji.ui.theme.CategorySelectedText
import com.example.kanji.ui.theme.CategoryTitle
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.ModeMeaningGradientEnd
import com.example.kanji.ui.theme.ModeMeaningGradientMid
import com.example.kanji.ui.theme.ModeMeaningGradientStart
import com.example.kanji.ui.theme.ModeReadingGradientEnd
import com.example.kanji.ui.theme.ModeReadingGradientMid
import com.example.kanji.ui.theme.ModeReadingGradientStart
import com.example.kanji.ui.theme.OverlayBlackSoft
import com.example.kanji.ui.theme.OverlayBlackStrong
import com.example.kanji.ui.theme.OverlayWhiteSoft
import com.example.kanji.ui.theme.SelectedBadgeText
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "เลือกโหมด",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = CategoryTitle
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
                    ModeReadingGradientStart,
                    ModeReadingGradientMid,
                    ModeReadingGradientEnd
                ),
                selected = selectedMode == PracticeMode.READING,
                onClick = { onModeSelected(PracticeMode.READING) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ModeSelectableCard(
                title = "ทายความหมาย",
                accentText = "文",
                gradientColors = listOf(
                    ModeMeaningGradientStart,
                    ModeMeaningGradientMid,
                    ModeMeaningGradientEnd
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
                color = CategorySelectedText
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
                        containerColor = BackButtonBg,
                        contentColor = BackButtonText
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
                        containerColor = GreenPrimary,
                        contentColor = BgWhite,
                        disabledContainerColor = GreenPrimary.copy(alpha = 0.5f),
                        disabledContentColor = BgWhite.copy(alpha = 0.8f)
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
                color = if (selected) BgWhite else Color.Transparent,
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
                    .background(OverlayWhiteSoft)
            )

            Box(
                modifier = Modifier
                    .size(95.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 28.dp)
                    .clip(CircleShape)
                    .background(OverlayBlackSoft)
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.68f)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                OverlayBlackStrong,
                                Color.Transparent
                            )
                        )
                    )
            )

            Text(
                text = title,
                color = BgWhite,
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
                    .background(BgWhite.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = accentText,
                    color = BgWhite,
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
                        .background(BgWhite.copy(alpha = 0.95f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "เลือกแล้ว",
                        color = SelectedBadgeText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}