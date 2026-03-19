package com.example.kanji.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: QuizResultEntity)

    @Query("SELECT * FROM quiz_results ORDER BY playedAt DESC LIMIT 5")
    fun observeLatestResults(): Flow<List<QuizResultEntity>>
}