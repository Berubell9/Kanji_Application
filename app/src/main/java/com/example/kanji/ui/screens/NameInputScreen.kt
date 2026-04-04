package com.example.kanji.ui.screens

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kanji.ui.theme.BgWhite
import com.example.kanji.ui.theme.BorderSoft
import com.example.kanji.ui.theme.CardWhite
import com.example.kanji.ui.theme.ErrorText
import com.example.kanji.ui.theme.GreenPrimary
import com.example.kanji.ui.theme.HomeTitle
import com.example.kanji.ui.theme.TextPrimary
import com.example.kanji.ui.theme.TextSecondary

@Composable
fun NameInputScreen(
    playerName: String,
    errorMessage: String?,
    onNameChange: (String) -> Unit,
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 2.dp,
            shadowElevation = 6.dp,
            color = CardWhite
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Kanji Quiz",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = HomeTitle
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = playerName,
                    onValueChange = onNameChange,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text(
                            text = "ระบุชื่อเล่น",
                            color = TextSecondary
                        )
                    },
                    placeholder = {
                        Text(
                            text = "เช่น ลูกศร, หลุน, เบล",
                            color = TextSecondary
                        )
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        cursorColor = GreenPrimary,
                        focusedBorderColor = GreenPrimary,
                        unfocusedBorderColor = BorderSoft,
                        focusedLabelColor = GreenPrimary,
                        unfocusedLabelColor = TextSecondary,
                        focusedPlaceholderColor = TextSecondary,
                        unfocusedPlaceholderColor = TextSecondary,
                        focusedContainerColor = BgWhite,
                        unfocusedContainerColor = BgWhite
                    )
                )

                if (!errorMessage.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = errorMessage,
                        color = ErrorText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onStartClick,
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
                        text = "เริ่ม",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}