package com.example.kanji.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface KanjiDao {

    @Query("SELECT * FROM kanji ORDER BY RANDOM()")
    suspend fun getAllRandom(): List<KanjiEntity>

    @Query("SELECT * FROM kanji ORDER BY id ASC")
    fun observeAll(): Flow<List<KanjiEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<KanjiEntity>)

    @Query("SELECT COUNT(*) FROM kanji")
    suspend fun count(): Int
}