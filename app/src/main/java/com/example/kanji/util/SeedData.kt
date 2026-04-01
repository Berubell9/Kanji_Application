package com.example.kanji.util

import com.example.kanji.data.local.KanjiEntity
import com.example.kanji.model.PracticeCategory

object SeedData {
    val kanjiItems = listOf(

        // ANIMALS
        KanjiEntity(kanji = "犬", reading = "いぬ", meaning = "สุนัข", imageName = "animal_dog", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "猫", reading = "ねこ", meaning = "แมว", imageName = "animal_cat", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "鳥", reading = "とり", meaning = "นก", imageName = "animal_bird", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "魚", reading = "さかな", meaning = "ปลา", imageName = "animal_fish", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "馬", reading = "うま", meaning = "ม้า", imageName = "animal_horse", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "牛", reading = "うし", meaning = "วัว", imageName = "animal_cow", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "豚", reading = "ぶた", meaning = "หมู", imageName = "animal_pig", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "羊", reading = "ひつじ", meaning = "แกะ", imageName = "animal_sheep", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "兎", reading = "うさぎ", meaning = "กระต่าย", imageName = "animal_rabbit", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "亀", reading = "かめ", meaning = "เต่า", imageName = "animal_turtle", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "猿", reading = "さる", meaning = "ลิง", imageName = "animal_monkey", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "象", reading = "ぞう", meaning = "ช้าง", imageName = "animal_elephant", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "虎", reading = "とら", meaning = "เสือ", imageName = "animal_tiger", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "鹿", reading = "しか", meaning = "กวาง", imageName = "animal_deer", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "熊", reading = "くま", meaning = "หมี", imageName = "animal_bear", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "狼", reading = "おおかみ", meaning = "หมาป่า", imageName = "animal_wolf", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "狐", reading = "きつね", meaning = "สุนัขจิ้งจอก", imageName = "animal_fox", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "虫", reading = "むし", meaning = "แมลง", imageName = "animal_insect", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "蝶", reading = "ちょう", meaning = "ผีเสื้อ", imageName = "animal_butterfly", category = PracticeCategory.ANIMALS.name),
        KanjiEntity(kanji = "鯨", reading = "くじら", meaning = "ปลาวาฬ", imageName = "animal_whale", category = PracticeCategory.ANIMALS.name),

        // OBJECTS
        KanjiEntity(kanji = "本", reading = "ほん", meaning = "หนังสือ", imageName = "object_book", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "机", reading = "つくえ", meaning = "โต๊ะ", imageName = "object_desk", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "椅子", reading = "いす", meaning = "เก้าอี้", imageName = "object_chair", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "車", reading = "くるま", meaning = "รถ", imageName = "object_car", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "時計", reading = "とけい", meaning = "นาฬิกา", imageName = "object_clock", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "電話", reading = "でんわ", meaning = "โทรศัพท์", imageName = "object_phone", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "鞄", reading = "かばん", meaning = "กระเป๋า", imageName = "object_bag", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "靴", reading = "くつ", meaning = "รองเท้า", imageName = "object_shoes", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "傘", reading = "かさ", meaning = "ร่ม", imageName = "object_umbrella", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "鍵", reading = "かぎ", meaning = "กุญแจ", imageName = "object_key", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "窓", reading = "まど", meaning = "หน้าต่าง", imageName = "object_window", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "門", reading = "もん", meaning = "ประตูใหญ่", imageName = "object_gate", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "紙", reading = "かみ", meaning = "กระดาษ", imageName = "object_paper", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "鉛筆", reading = "えんぴつ", meaning = "ดินสอ", imageName = "object_pencil", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "皿", reading = "さら", meaning = "จาน", imageName = "object_plate", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "服", reading = "ふく", meaning = "เสื้อผ้า", imageName = "object_clothes", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "帽子", reading = "ぼうし", meaning = "หมวก", imageName = "object_hat", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "箱", reading = "はこ", meaning = "กล่อง", imageName = "object_box", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "眼鏡", reading = "めがね", meaning = "แว่นตา", imageName = "object_glasses", category = PracticeCategory.OBJECTS.name),
        KanjiEntity(kanji = "自転車", reading = "じてんしゃ", meaning = "จักรยาน", imageName = "object_bicycle", category = PracticeCategory.OBJECTS.name),

        // VERBS
        KanjiEntity(kanji = "行く", reading = "いく", meaning = "ไป", imageName = "verb_go", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "来る", reading = "くる", meaning = "มา", imageName = "verb_come", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "見る", reading = "みる", meaning = "ดู", imageName = "verb_see", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "聞く", reading = "きく", meaning = "ฟัง", imageName = "verb_listen", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "話す", reading = "はなす", meaning = "พูด", imageName = "verb_speak", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "読む", reading = "よむ", meaning = "อ่าน", imageName = "verb_read", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "書く", reading = "かく", meaning = "เขียน", imageName = "verb_write", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "食べる", reading = "たべる", meaning = "กิน", imageName = "verb_eat", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "飲む", reading = "のむ", meaning = "ดื่ม", imageName = "verb_drink", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "買う", reading = "かう", meaning = "ซื้อ", imageName = "verb_buy", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "遊ぶ", reading = "あそぶ", meaning = "เล่น", imageName = "verb_play", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "歌う", reading = "うたう", meaning = "ร้องเพลง", imageName = "verb_sing", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "立つ", reading = "たつ", meaning = "ยืน", imageName = "verb_stand", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "座る", reading = "すわる", meaning = "นั่ง", imageName = "verb_sit", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "歩く", reading = "あるく", meaning = "เดิน", imageName = "verb_walk", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "走る", reading = "はしる", meaning = "วิ่ง", imageName = "verb_run", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "寝る", reading = "ねる", meaning = "นอน", imageName = "verb_sleep", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "起きる", reading = "おきる", meaning = "ตื่น", imageName = "verb_wake_up", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "作る", reading = "つくる", meaning = "ทำ", imageName = "verb_make", category = PracticeCategory.VERBS.name),
        KanjiEntity(kanji = "開ける", reading = "あける", meaning = "เปิด", imageName = "verb_open", category = PracticeCategory.VERBS.name)
    )
}