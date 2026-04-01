package com.example.kanji.util

import com.example.kanji.R
import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.model.PracticeCategory

object SeedData {
    val kanjiItems = listOf(
        KanjiEntity(
            kanji = "犬",
            reading = "いぬ",
            meaning = "สุนัข",
            imageRes = R.drawable.dog,
            category = PracticeCategory.ANIMALS.name
        ),
        KanjiEntity(
            kanji = "猫",
            reading = "ねこ",
            meaning = "แมว",
            imageRes = R.drawable.cat,
            category = PracticeCategory.ANIMALS.name
        ),
        KanjiEntity(
            kanji = "水",
            reading = "みず",
            meaning = "น้ำ",
            imageRes = R.drawable.water,
            category = PracticeCategory.ELEMENTS.name
        ),
        KanjiEntity(
            kanji = "火",
            reading = "ひ",
            meaning = "ไฟ",
            imageRes = R.drawable.fire,
            category = PracticeCategory.ELEMENTS.name
        )
    )
}