package com.example.kanji.data.repository

import com.example.kanji.data.local.KanjiDao
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.data.local.QuizResultDao
import com.example.kanji.data.local.QuizResultEntity
import com.example.kanji.model.PracticeCategory
import com.example.kanji.util.SeedData
import kotlinx.coroutines.flow.Flow

class KanjiRepository(
    private val kanjiDao: KanjiDao,
    private val quizResultDao: QuizResultDao
) {

    suspend fun seedIfEmpty() {
        if (kanjiDao.count() == 0) {
            kanjiDao.insertAll(SeedData.kanjiItems)
        }
    }

    suspend fun getQuizQuestions(category: PracticeCategory): List<KanjiEntity> {
        return when (category) {
            PracticeCategory.ALL -> kanjiDao.getAllRandom()
            else -> kanjiDao.getByCategoryRandom(category.name)
        }
    }

    fun observeLatestResults(): Flow<List<QuizResultEntity>> {
        return quizResultDao.observeLatestResults()
    }

    suspend fun saveResult(mode: String, score: Int, total: Int) {
        quizResultDao.insert(
            QuizResultEntity(
                mode = mode,
                score = score,
                total = total
            )
        )
    }
}