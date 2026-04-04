package com.example.kanji.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kanji.ui.theme.BgWhite
import com.example.kanji.ui.theme.BorderSoft
import com.example.kanji.ui.theme.CardWhite
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.HomeTitle
import com.example.kanji.ui.theme.QuestionBg
import com.example.kanji.ui.theme.SecondaryButtonText
import com.example.kanji.ui.theme.TextPrimary
import com.example.kanji.ui.theme.TextSecondary

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onReplay: () -> Unit,
    onBackHome: () -> Unit
) {
    val percent = if (total > 0) (score * 100) / total else 0

    val message = when {
        percent == 100 -> "ยอดเยี่ยมมาก"
        percent >= 75 -> "เก่งมาก"
        percent >= 50 -> "ดีมาก ลองอีกครั้งเพื่อคะแนนที่สูงขึ้น"
        else -> "ลองฝึกต่ออีกนิด"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 2.dp,
            shadowElevation = 6.dp,
            color = CardWhite
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "สรุปผลการฝึก",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = HomeTitle
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = QuestionBg
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$score / $total",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "คิดเป็น $percent%",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onReplay,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenPrimary,
                        contentColor = BgWhite
                    )
                ) {
                    Text(
                        text = "เล่นอีกครั้ง",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedButton(
                    onClick = onBackHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(1.dp, BorderSoft),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = BgWhite,
                        contentColor = SecondaryButtonText
                    )
                ) {
                    Text(
                        text = "กลับหน้าแรก",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}