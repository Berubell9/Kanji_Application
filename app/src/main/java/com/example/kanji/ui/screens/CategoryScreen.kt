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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.foundation.layout.fillMaxHeight

data class CategoryCardStyle(
    val title: String,
    val imageRes: Int,
    val gradient: List<Color>
)

@Composable
fun CategoryScreen(
    playerName: String,
    selectedCategory: PracticeCategory,
    onCategorySelected: (PracticeCategory) -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val categoryStyles = listOf(
        PracticeCategory.ALL to CategoryCardStyle(
            title = "ทั้งหมด",
            imageRes = R.drawable.cat,
            gradient = listOf(
                Color(0xFF14001F),
                Color(0xFF3B0A6B),
                Color(0xFF5B21B6)
            )
        ),
        PracticeCategory.ANIMALS to CategoryCardStyle(
            title = "สัตว์",
            imageRes = R.drawable.dog,
            gradient = listOf(
                Color(0xFF2B0008),
                Color(0xFFB00020),
                Color(0xFFFF1744)
            )
        ),
        PracticeCategory.ELEMENTS to CategoryCardStyle(
            title = "ธรรมชาติ",
            imageRes = R.drawable.water,
            gradient = listOf(
                Color(0xFF3A1D00),
                Color(0xFFF57C00),
                Color(0xFFFFB300)
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "เลือกหมวดหมู่",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "ผู้เล่น: $playerName",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(categoryStyles) { (category, style) ->
                CategoryWideCard(
                    title = style.title,
                    imageRes = style.imageRes,
                    gradientColors = style.gradient,
                    selected = selectedCategory == category,
                    onClick = { onCategorySelected(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "หมวดที่เลือก: ${
                when (selectedCategory) {
                    PracticeCategory.ALL -> "ทั้งหมด"
                    PracticeCategory.ANIMALS -> "สัตว์"
                    PracticeCategory.ELEMENTS -> "ธรรมชาติ"
                }
            }",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text("ย้อนกลับ", color = MaterialTheme.colorScheme.onSurface)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .weight(1f)
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary
                )
            ) {
                Text("ถัดไป", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CategoryWideCard(
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
            .height(138.dp)
            .clip(shape)
            .border(
                width = if (selected) 3.dp else 0.dp,
                color = if (selected) Color.White else Color.Transparent,
                shape = shape
            )
            .clickable { onClick() },
        shape = shape,
        shadowElevation = if (selected) 10.dp else 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(gradientColors)
                )
        ) {
            // รูปด้านขวา
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(150.dp)
            )

            // overlay มืดฝั่งซ้ายให้อ่านตัวอักษรง่าย
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.72f)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.35f),
                                Color.Transparent
                            )
                        )
                    )
            )

            // วงกลม/ลายตกแต่งด้านบนคล้ายตัวอย่าง
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 0.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.12f))
            )

            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 36.dp, top = 0.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.12f))
            )

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, bottom = 18.dp)
            )

            if (selected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.9f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "เลือกแล้ว",
                        color = Color(0xFF222222),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}