package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
TODO#1
Уровни сложности игры.(TEST - только для разработки)
TODO#5
enum class - неявно реализуют Serializable
 */
@Parcelize
enum class Level : Parcelable {

    TEST, EASY, NORMAL, HARD
}