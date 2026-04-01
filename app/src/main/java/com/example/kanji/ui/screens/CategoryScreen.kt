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
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.TextSecondary

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
            Text(
                text = "เลือกหมวดหมู่",
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

            Spacer(modifier = Modifier.height(18.dp))

            CategorySelectableCard(
                title = "ทั้งหมด",
                imageRes = R.drawable.object_book,
                gradientColors = listOf(
                    Color(0xFF3A185E),
                    Color(0xFF6F2DBD),
                    Color(0xFF9D4EDD)
                ),
                selected = selectedCategory == PracticeCategory.ALL,
                onClick = { onCategorySelected(PracticeCategory.ALL) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "สัตว์",
                imageRes = R.drawable.animal_dog,
                gradientColors = listOf(
                    Color(0xFF1F4D2E),
                    Color(0xFF2E8B57),
                    Color(0xFF57CC99)
                ),
                selected = selectedCategory == PracticeCategory.ANIMALS,
                onClick = { onCategorySelected(PracticeCategory.ANIMALS) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "สิ่งของ",
                imageRes = R.drawable.object_book,
                gradientColors = listOf(
                    Color(0xFF6B3E12),
                    Color(0xFFD97706),
                    Color(0xFFFBBF24)
                ),
                selected = selectedCategory == PracticeCategory.OBJECTS,
                onClick = { onCategorySelected(PracticeCategory.OBJECTS) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategorySelectableCard(
                title = "กริยา",
                imageRes = R.drawable.verb_run,
                gradientColors = listOf(
                    Color(0xFF7A1029),
                    Color(0xFFD6336C),
                    Color(0xFFFF8FAB)
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
                        contentColor = Color.White
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
                .background(brush = Brush.horizontalGradient(gradientColors))
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
                            listOf(Color.Black.copy(alpha = 0.20f), Color.Transparent)
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
                color = Color.White,
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