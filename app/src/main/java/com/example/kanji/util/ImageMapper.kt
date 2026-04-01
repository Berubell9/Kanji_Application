package com.example.kanji.util

import com.example.kanji.R

object ImageMapper {
    fun getImageRes(imageName: String): Int {
        return when (imageName) {
            "animal_dog" -> R.drawable.animal_dog
            "animal_cat" -> R.drawable.animal_cat
            "animal_bird" -> R.drawable.animal_bird
            "animal_fish" -> R.drawable.animal_fish
            "animal_horse" -> R.drawable.animal_horse
            "animal_cow" -> R.drawable.animal_cow
            "animal_pig" -> R.drawable.animal_pig
            "animal_sheep" -> R.drawable.animal_sheep
            "animal_rabbit" -> R.drawable.animal_rabbit
            "animal_turtle" -> R.drawable.animal_turtle
            "animal_monkey" -> R.drawable.animal_monkey
            "animal_elephant" -> R.drawable.animal_elephant
            "animal_tiger" -> R.drawable.animal_tiger
            "animal_deer" -> R.drawable.animal_deer
            "animal_bear" -> R.drawable.animal_bear
            "animal_wolf" -> R.drawable.animal_wolf
            "animal_fox" -> R.drawable.animal_fox
            "animal_insect" -> R.drawable.animal_insect
            "animal_butterfly" -> R.drawable.animal_butterfly
            "animal_whale" -> R.drawable.animal_whale

            "object_book" -> R.drawable.object_book
            "object_desk" -> R.drawable.object_desk
            "object_chair" -> R.drawable.object_chair
            "object_car" -> R.drawable.object_car
            "object_clock" -> R.drawable.object_clock
            "object_phone" -> R.drawable.object_phone
            "object_bag" -> R.drawable.object_bag
            "object_shoes" -> R.drawable.object_shoes
            "object_umbrella" -> R.drawable.object_umbrella
            "object_key" -> R.drawable.object_key
            "object_window" -> R.drawable.object_window
            "object_gate" -> R.drawable.object_gate
            "object_paper" -> R.drawable.object_paper
            "object_pencil" -> R.drawable.object_pencil
            "object_plate" -> R.drawable.object_plate
            "object_clothes" -> R.drawable.object_clothes
            "object_hat" -> R.drawable.object_hat
            "object_box" -> R.drawable.object_box
            "object_glasses" -> R.drawable.object_glasses
            "object_bicycle" -> R.drawable.object_bicycle

            "verb_go" -> R.drawable.verb_go
            "verb_come" -> R.drawable.verb_come
            "verb_see" -> R.drawable.verb_see
            "verb_listen" -> R.drawable.verb_listen
            "verb_speak" -> R.drawable.verb_speak
            "verb_read" -> R.drawable.verb_read
            "verb_write" -> R.drawable.verb_write
            "verb_eat" -> R.drawable.verb_eat
            "verb_drink" -> R.drawable.verb_drink
            "verb_buy" -> R.drawable.verb_buy
            "verb_play" -> R.drawable.verb_play
            "verb_stand" -> R.drawable.verb_stand
            "verb_sit" -> R.drawable.verb_sit
            "verb_walk" -> R.drawable.verb_walk
            "verb_run" -> R.drawable.verb_run
            "verb_sleep" -> R.drawable.verb_sleep
            "verb_wake_up" -> R.drawable.verb_wake_up
            "verb_make" -> R.drawable.verb_make
            "verb_sing" -> R.drawable.verb_sing
            "verb_open" -> R.drawable.verb_open

            else -> R.drawable.object_box
        }
    }
}