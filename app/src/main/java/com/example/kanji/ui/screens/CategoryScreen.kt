package com.example.kanji.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kanji.R
import com.example.kanji.model.PracticeCategory
import com.example.kanji.ui.theme.AllGradientEnd
import com.example.kanji.ui.theme.AllGradientMid
import com.example.kanji.ui.theme.AllGradientStart
import com.example.kanji.ui.theme.AnimalGradientEnd
import com.example.kanji.ui.theme.AnimalGradientMid
import com.example.kanji.ui.theme.AnimalGradientStart
import com.example.kanji.ui.theme.BackButtonBg
import com.example.kanji.ui.theme.BackButtonText
import com.example.kanji.ui.theme.BgWhite
import com.example.kanji.ui.theme.CategorySelectedText
import com.example.kanji.ui.theme.CategoryTitle
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.ObjectGradientEnd
import com.example.kanji.ui.theme.ObjectGradientMid
import com.example.kanji.ui.theme.ObjectGradientStart
import com.example.kanji.ui.theme.OverlayBlackSoft
import com.example.kanji.ui.theme.OverlayBlackStrong
import com.example.kanji.ui.theme.OverlayWhiteSoft
import com.example.kanji.ui.theme.SelectedBadgeText
import com.example.kanji.ui.theme.TextSecondary
import com.example.kanji.ui.theme.VerbGradientEnd
import com.example.kanji.ui.theme.VerbGradientMid
import com.example.kanji.ui.theme.VerbGradientStart

@Composable
fun CategoryScreen(
    playerName: String,
    selectedCategory: PracticeCategory,
    onCategorySelected: (PracticeCategory) -> Unit,
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
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "เลือกหมวดหมู่",
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

            Spacer(modifier = Modifier.height(18.dp))

            CategorySelectableCard(
                title = "ทั้งหมด",
                imageRes = R.drawable.object_book,
                gradientColors = listOf(
                    AllGradientStart,
                    AllGradientMid,
                    AllGradientEnd
                ),
                selected = selectedCategory == PracticeCategory.ALL,
                onClick = { onCategorySelected(PracticeCategory.ALL) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "สัตว์",
                imageRes = R.drawable.animal_dog,
                gradientColors = listOf(
                    AnimalGradientStart,
                    AnimalGradientMid,
                    AnimalGradientEnd
                ),
                selected = selectedCategory == PracticeCategory.ANIMALS,
                onClick = { onCategorySelected(PracticeCategory.ANIMALS) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "สิ่งของ",
                imageRes = R.drawable.object_book,
                gradientColors = listOf(
                    ObjectGradientStart,
                    ObjectGradientMid,
                    ObjectGradientEnd
                ),
                selected = selectedCategory == PracticeCategory.OBJECTS,
                onClick = { onCategorySelected(PracticeCategory.OBJECTS) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "กริยา",
                imageRes = R.drawable.verb_run,
                gradientColors = listOf(
                    VerbGradientStart,
                    VerbGradientMid,
                    VerbGradientEnd
                ),
                selected = selectedCategory == PracticeCategory.VERBS,
                onClick = { onCategorySelected(PracticeCategory.VERBS) }
            )

            Spacer(modifier = Modifier.height(140.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "หมวดที่เลือก: ${selectedCategory.displayName}",
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
                    Text("ย้อนกลับ", fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenPrimary,
                        contentColor = BgWhite
                    )
                ) {
                    Text("ถัดไป", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun CategorySelectableCard(
    title: String,
    imageRes: Int,
    gradientColors: List<Color>,
    selected: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
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
                .background(brush = Brush.horizontalGradient(gradientColors))
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
                            listOf(OverlayBlackStrong, Color.Transparent)
                        )
                    )
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(128.dp)
                    .clip(RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp))
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