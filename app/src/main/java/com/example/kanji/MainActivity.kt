@file:Suppress("FunctionName")

package com.example.kanji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F7FB)
                ) {
                    KanjiQuizApp()
                }
            }
        }
    }
}

enum class GameMode {
    READING,
    MEANING
}

enum class AppScreen {
    HOME,
    QUIZ,
    RESULT
}

data class KanjiItem(
    val kanji: String,
    val reading: String,
    val meaning: String,
    @DrawableRes val imageRes: Int
)

val questionBank = listOf(
    KanjiItem("犬", "いぬ", "สุนัข", R.drawable.dog),
    KanjiItem("猫", "ねこ", "แมว", R.drawable.cat),
    KanjiItem("水", "みず", "น้ำ", R.drawable.water),
    KanjiItem("火", "ひ", "ไฟ", R.drawable.fire)
)

@Composable
fun KanjiQuizApp() {
    var screen by remember { mutableStateOf(AppScreen.HOME) }
    var mode by remember { mutableStateOf(GameMode.READING) }

    var questions by remember { mutableStateOf(questionBank.shuffled()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var options by remember { mutableStateOf(listOf<String>()) }

    fun getCorrectAnswer(item: KanjiItem): String {
        return when (mode) {
            GameMode.READING -> item.reading
            GameMode.MEANING -> item.meaning
        }
    }

    fun buildOptions(item: KanjiItem): List<String> {
        val correct = getCorrectAnswer(item)

        val allChoices = when (mode) {
            GameMode.READING -> questionBank.map { it.reading }
            GameMode.MEANING -> questionBank.map { it.meaning }
        }

        val pool = allChoices
            .filter { it != correct }
            .distinct()
            .shuffled()

        return (listOf(correct) + pool.take(3)).shuffled()
    }

    fun startQuiz(newMode: GameMode) {
        mode = newMode
        questions = questionBank.shuffled()
        currentIndex = 0
        score = 0
        selectedAnswer = null
        options = buildOptions(questions[currentIndex])
        screen = AppScreen.QUIZ
    }

    fun goNext() {
        if (currentIndex == questions.lastIndex) {
            screen = AppScreen.RESULT
        } else {
            currentIndex++
            selectedAnswer = null
            options = buildOptions(questions[currentIndex])
        }
    }

    when (screen) {
        AppScreen.HOME -> {
            HomeScreen(
                onReadingClick = { startQuiz(GameMode.READING) },
                onMeaningClick = { startQuiz(GameMode.MEANING) }
            )
        }

        AppScreen.QUIZ -> {
            val currentQuestion = questions[currentIndex]
            val correctAnswer = getCorrectAnswer(currentQuestion)

            QuizScreen(
                mode = mode,
                question = currentQuestion,
                options = options,
                questionNumber = currentIndex + 1,
                totalQuestions = questions.size,
                score = score,
                selectedAnswer = selectedAnswer,
                onAnswerClick = { answer ->
                    if (selectedAnswer == null) {
                        selectedAnswer = answer
                        if (answer == correctAnswer) {
                            score++
                        }
                    }
                },
                onNextClick = { goNext() }
            )
        }

        AppScreen.RESULT -> {
            ResultScreen(
                score = score,
                total = questions.size,
                onReplay = { startQuiz(mode) },
                onBackHome = { screen = AppScreen.HOME }
            )
        }
    }
}

@Composable
fun HomeScreen(
    onReadingClick: () -> Unit,
    onMeaningClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kanji Quiz",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "แอปฝึกคันจิแบบเลือกคำตอบ 4 ตัวเลือก",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onReadingClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF58CC02)
            )
        ) {
            Text("โหมดทายการอ่าน")
        }

        Spacer(modifier = Modifier.height(16.dp))

        FilledTonalButton(
            onClick = onMeaningClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("โหมดทายความหมาย")
        }
    }
}

@Composable
fun QuizScreen(
    mode: GameMode,
    question: KanjiItem,
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
            Text("ข้อ $questionNumber / $totalQuestions", fontWeight = FontWeight.Bold)
            Text("คะแนน $score", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { progressValue },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(12.dp)),
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

        if (options.size >= 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = spacedBy(12.dp)
            ) {
                ChoiceButton(
                    text = options[0],
                    isCorrect = options[0] == correctAnswer,
                    isSelected = selectedAnswer == options[0],
                    enabled = selectedAnswer == null,
                    modifier = Modifier.weight(1f),
                    onClick = { onAnswerClick(options[0]) }
                )
                ChoiceButton(
                    text = options[1],
                    isCorrect = options[1] == correctAnswer,
                    isSelected = selectedAnswer == options[1],
                    enabled = selectedAnswer == null,
                    modifier = Modifier.weight(1f),
                    onClick = { onAnswerClick(options[1]) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = spacedBy(12.dp)
            ) {
                ChoiceButton(
                    text = options[2],
                    isCorrect = options[2] == correctAnswer,
                    isSelected = selectedAnswer == options[2],
                    enabled = selectedAnswer == null,
                    modifier = Modifier.weight(1f),
                    onClick = { onAnswerClick(options[2]) }
                )
                ChoiceButton(
                    text = options[3],
                    isCorrect = options[3] == correctAnswer,
                    isSelected = selectedAnswer == options[3],
                    enabled = selectedAnswer == null,
                    modifier = Modifier.weight(1f),
                    onClick = { onAnswerClick(options[3]) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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
fun ChoiceButton(
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

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onReplay: () -> Unit,
    onBackHome: () -> Unit
) {
    val percent = if (total > 0) (score * 100) / total else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "จบเกมแล้ว",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "คุณได้ $score / $total คะแนน",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "คิดเป็น $percent%",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onReplay,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF58CC02)
            )
        ) {
            Text("เล่นอีกครั้ง")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onBackHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("กลับหน้าแรก")
        }
    }
}