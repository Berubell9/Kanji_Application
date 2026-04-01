package com.example.kanji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kanji.ui.KanjiQuizApp
import com.example.kanji.ui.KanjiViewModel
import com.example.kanji.ui.theme.KanjiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as KanjiApplication

        setContent {
            KanjiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val kanjiViewModel: KanjiViewModel = viewModel(
                        factory = KanjiViewModel.provideFactory(app.repository)
                    )

                    KanjiQuizApp(viewModel = kanjiViewModel)
                }
            }
        }
    }
}